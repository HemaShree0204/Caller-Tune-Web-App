package CRBT.Notifications_Service.Exception;

public class OtpSendFailedException extends RuntimeException {
    public OtpSendFailedException(String message) {
        super(message);
    }
}
