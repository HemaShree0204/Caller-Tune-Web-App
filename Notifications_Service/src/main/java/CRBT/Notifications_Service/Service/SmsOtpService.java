package CRBT.Notifications_Service.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class SmsOtpService {

    @Value("${message.central.customer-id}")
    private String customerId;

    @Value("${message.central.auth-token}")  // Auth token given in dashboard
    private String authToken;

    private final RestTemplate rest = new RestTemplate();

    /**
     * Request MessageCentral to send an OTP to the given phone number.
     * OTP will be generated & delivered by MessageCentral (not by us).
     */
    public String sendOtp(String phoneNumber) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("authToken", authToken);

        String url = UriComponentsBuilder
                .fromHttpUrl("https://cpaas.messagecentral.com/verification/v3/send")
                .queryParam("countryCode", "91")
                .queryParam("flowType", "SMS")
                .queryParam("type", "OTP")
                .queryParam("mobileNumber", phoneNumber)
                .toUriString();

        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<Map> response = rest.exchange(url, HttpMethod.POST, request, Map.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("OTP send failed: " + response.getStatusCode());
        }

        Map body = response.getBody();
        if (body == null || body.get("data") == null) {
            throw new RuntimeException("Invalid response from MessageCentral");
        }

        Map data = (Map) body.get("data");
        return data.get("verificationId").toString();
    }

    /**
     * Validate OTP entered by user against MessageCentral verify API.
     */
    public boolean verifyOtp(String verificationId, String otp) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("authToken", authToken);

        String url = UriComponentsBuilder
                .fromHttpUrl("https://cpaas.messagecentral.com/verification/v3/validateOtp")
                .queryParam("verificationId", verificationId)
                .queryParam("code", otp)
                .toUriString();

        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<Map> response = rest.exchange(url, HttpMethod.GET, request, Map.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            return false;
        }

        Map body = response.getBody();
        if (body == null || body.get("data") == null) {
            return false;
        }

        Map data = (Map) body.get("data");
        String status = (String) data.get("verificationStatus");
        return "VERIFICATION_COMPLETED".equalsIgnoreCase(status);
    }
}
