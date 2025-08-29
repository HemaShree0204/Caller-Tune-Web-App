package CRBT.Notifications_Service.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import CRBT.Notifications_Service.DTO.OtpRequestDto;
import CRBT.Notifications_Service.DTO.OtpVerifyDto;
import CRBT.Notifications_Service.Service.SmsOtpService;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

    @Autowired
    private SmsOtpService smsOtpService;

    @PostMapping("/send")
    public ResponseEntity<String> sendOtp(@Valid @RequestBody OtpRequestDto dto) {
    	 try {
    	        String verificationId = smsOtpService.sendOtp(dto.getPhoneNumber());
    	        return ResponseEntity.ok("OTP sent successfully. Verification ID: " + verificationId);
    	    } catch (Exception e) {
    	        return ResponseEntity.status(500).body("Failed to send OTP: " + e.getMessage());
    	    }
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(@Valid @RequestBody OtpVerifyDto dto) {
    	try {
            boolean isValid = smsOtpService.verifyOtp(dto.getPhoneNumber(), dto.getOtp());
            if (isValid) {
                return ResponseEntity.ok("OTP verified successfully.");
            } else {
                return ResponseEntity.badRequest().body("Invalid or expired OTP.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("OTP verification failed: " + e.getMessage());
        }
    }
}
