package CRBT.Notifications_Service.DTO;

import jakarta.validation.constraints.NotBlank;

public class OtpVerifyDto {

    @NotBlank(message = "Phone number must not be blank")
    private String phoneNumber;

    @NotBlank(message = "OTP must not be blank")
    private String otp;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
