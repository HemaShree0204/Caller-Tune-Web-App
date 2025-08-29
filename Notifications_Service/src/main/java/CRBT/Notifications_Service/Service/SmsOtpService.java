package CRBT.Notifications_Service.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SmsOtpService {

	@Value("${message.central.customer-id}")
    private String customerId;

    @Value("${message.central.password-base64}")  // this is actually JWT token
    private String base64Password;

    private final RestTemplate rest = new RestTemplate();

    private final Map<String, OtpCache> otpStore = new ConcurrentHashMap<>();
    private final long OTP_VALIDITY_SECONDS = 300; // 5 minutes
    
    private String authToken;
    private long tokenExpiryEpoch;
    private String getAuthToken() {
        if (authToken != null && Instant.now().getEpochSecond() < tokenExpiryEpoch) {
            return authToken;
        }

        String url = String.format(
            "https://cpaas.messagecentral.com/auth/v1/authentication/token?customerId=%s&key=%s&scope=NEW",
            customerId, base64Password);

        Map<String, Object> response = rest.getForObject(url, Map.class);

        if (response != null && response.get("token") != null) {
            authToken = response.get("token").toString();
            tokenExpiryEpoch = Instant.now().getEpochSecond() + 300; // cache 5 mins
            return authToken;
        }
        throw new RuntimeException("Failed to get auth token");
    }

    public String sendOtp(String phoneNumber) {
    	String otp = generateOtp();

        HttpHeaders headers = new HttpHeaders();
        headers.set("authToken", base64Password);  // directly use this token

        String message = "Your OTP is " + otp;

        String url = UriComponentsBuilder
            .fromHttpUrl("https://cpaas.messagecentral.com/verification/v3/send")
            .queryParam("countryCode", "91")
            .queryParam("flowType", "SMS")
            .queryParam("mobileNumber", phoneNumber)
            .queryParam("senderId", "UTOMOB")
            .queryParam("type", "SMS")
            .queryParam("message", message)
            .toUriString();

        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<Map> response = rest.exchange(url, HttpMethod.POST, request, Map.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("OTP send failed: " + response.getStatusCode());
        }

        Map data = (Map) response.getBody().get("data");
        String verificationId = data.get("verificationId").toString();

        otpStore.put(phoneNumber, new OtpCache(otp, verificationId, Instant.now().getEpochSecond() + OTP_VALIDITY_SECONDS));

        return verificationId;
    }

    public boolean verifyOtp(String phoneNumber, String otp) {
        OtpCache cache = otpStore.get(phoneNumber);

        if (cache == null) return false;

        long now = Instant.now().getEpochSecond();
        if (now > cache.getExpiry()) {
            otpStore.remove(phoneNumber);
            return false; // OTP expired
        }

        if (cache.getOtp().equals(otp)) {
            otpStore.remove(phoneNumber); // OTP verified, remove it
            return true;
        }

        return false; // OTP mismatch
    }

    private String generateOtp() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }

    private static class OtpCache {
        private final String otp;
        private final String verificationId;
        private final long expiry;

        public OtpCache(String otp, String verificationId, long expiry) {
            this.otp = otp;
            this.verificationId = verificationId;
            this.expiry = expiry;
        }

        public String getOtp() {
            return otp;
        }

        public String getVerificationId() {
            return verificationId;
        }

        public long getExpiry() {
            return expiry;
        }
        
    }
    }
