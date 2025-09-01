package CRBT.Notifications_Service.Service;

import CRBT.Notifications_Service.Exception.OtpSendFailedException;
import CRBT.Notifications_Service.Exception.OtpVerificationFailedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SmsOtpServiceTest {

    @InjectMocks
    private SmsOtpService smsOtpService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendOtp_Success() {
    	 Map<String, Object> data = new HashMap<>();
         data.put("verificationId", "12345");
         Map<String, Object> body = new HashMap<>();
         body.put("data", data);

         ResponseEntity<Map> response = new ResponseEntity<>(body, HttpStatus.OK);

         when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(Map.class)))
                 .thenReturn(response);

         String verificationId = smsOtpService.sendOtp("9999999999");
         assertEquals("12345", verificationId);
    }

    @Test
    void testSendOtp_FailureStatus() {
        ResponseEntity<Map> response = new ResponseEntity<>(Collections.emptyMap(), HttpStatus.INTERNAL_SERVER_ERROR);

        when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(Map.class)))
                .thenReturn(response);

        assertThrows(OtpSendFailedException.class, () -> smsOtpService.sendOtp("9999999999"));
    }

    @Test
    void testVerifyOtp_Success() {
    	Map<String, Object> data = new HashMap<>();
        data.put("verificationStatus", "VERIFICATION_COMPLETED");
        Map<String, Object> body = new HashMap<>();
        body.put("data", data);

        ResponseEntity<Map> response = new ResponseEntity<>(body, HttpStatus.OK);

        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(Map.class)))
                .thenReturn(response);

        boolean result = smsOtpService.verifyOtp("12345", "6789");
        assertTrue(result);
    }

    @Test
    void testVerifyOtp_Failure() {
        Map<String, Object> data = new HashMap<>();
        data.put("verificationStatus", "EXPIRED");
        Map<String, Object> body = new HashMap<>();
        body.put("data", data);

        ResponseEntity<Map> response = new ResponseEntity<>(body, HttpStatus.OK);

        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(Map.class)))
                .thenReturn(response);

        assertThrows(OtpVerificationFailedException.class, () -> smsOtpService.verifyOtp("12345", "6789"));
    }
}
