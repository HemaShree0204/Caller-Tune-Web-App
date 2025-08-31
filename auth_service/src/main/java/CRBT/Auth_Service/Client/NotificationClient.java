package CRBT.Auth_Service.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import CRBT.Auth_Service.DTO.OtpRequestDto;
import CRBT.Auth_Service.DTO.OtpVerifyDto;

@FeignClient(name = "notification-service")
public interface NotificationClient {

    @PostMapping("/send")
    String sendOtp(@RequestBody OtpRequestDto requestDto);

    @PostMapping("/verify")
    String verifyOtp(@RequestBody OtpVerifyDto verifyDto);
}
