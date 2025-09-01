package CRBT.Notifications_Service.Exception;

public class OtpVerificationFailedException extends RuntimeException {
    public OtpVerificationFailedException(String message) {
        super(message);
    }
}
