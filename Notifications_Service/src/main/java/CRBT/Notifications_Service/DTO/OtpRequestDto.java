package CRBT.Notifications_Service.DTO;

import jakarta.validation.constraints.NotBlank;

public class OtpRequestDto {

    @NotBlank(message = "Phone number must not be blank")
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
