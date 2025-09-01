package CRBT.Notifications_Service.Controller;

import CRBT.Notifications_Service.DTO.OtpRequestDto;
import CRBT.Notifications_Service.DTO.OtpVerifyDto;
import CRBT.Notifications_Service.Service.SmsOtpService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class OtpControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private OtpController otpController;

    @Mock
    private SmsOtpService smsOtpService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(otpController).build();
    }

    @Test
    void testSendOtp_Success() throws Exception {
        // Mock the service
        when(smsOtpService.sendOtp(anyString())).thenReturn("12345");

        String requestJson = "{\"phoneNumber\":\"9999999999\"}";

        mockMvc.perform(post("/api/otp/send")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string("OTP sent successfully. Verification ID: 12345"));

        // Verify that service method was called
        verify(smsOtpService).sendOtp("9999999999");
    }

    @Test
    void testVerifyOtp_Success() throws Exception {
        // Mock the service
        when(smsOtpService.verifyOtp(anyString(), anyString())).thenReturn(true);

        String requestJson = "{\"phoneNumber\":\"9999999999\",\"otp\":\"6789\"}";

        mockMvc.perform(post("/api/otp/verify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string("OTP verified successfully."));

        // Verify that service method was called
        verify(smsOtpService).verifyOtp("9999999999", "6789");
    }

    @Test
    void testVerifyOtp_Failure() throws Exception {
        // Mock the service to fail
        when(smsOtpService.verifyOtp(anyString(), anyString())).thenReturn(false);

        String requestJson = "{\"phoneNumber\":\"9999999999\",\"otp\":\"0000\"}";

        mockMvc.perform(post("/api/otp/verify")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid or expired OTP."));

        verify(smsOtpService).verifyOtp("9999999999", "0000");
    }
}
