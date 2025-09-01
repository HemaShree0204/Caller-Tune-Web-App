package CRBT.User_Service.Exceptions;
public class DuplicateMobileNumberException extends RuntimeException {
    public DuplicateMobileNumberException(String message) {
        super(message);
    }
}