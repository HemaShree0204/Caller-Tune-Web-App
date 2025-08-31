package CRBT.Auth_Service.DTO;

public class OtpVerifyDto {
    private String phoneNumber;
    private String otp;
    
    public OtpVerifyDto() {}

    public OtpVerifyDto(String phoneNumber, String otp) {
        this.phoneNumber = phoneNumber;
        this.otp = otp;
    }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getOtp() { return otp; }
    public void setOtp(String otp) { this.otp = otp; }
}