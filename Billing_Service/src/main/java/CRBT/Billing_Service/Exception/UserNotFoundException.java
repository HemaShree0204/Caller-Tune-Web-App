package CRBT.Billing_Service.Exception;

public class UserNotFoundException extends RuntimeException{
	public UserNotFoundException(String message) {
        super(message);
    }

}
