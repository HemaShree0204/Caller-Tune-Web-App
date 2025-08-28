package CRBT.Billing_Service.Exception;

public class InvalidBillingStatusException extends RuntimeException{
	public InvalidBillingStatusException(String message) {
        super(message);
    }

}
