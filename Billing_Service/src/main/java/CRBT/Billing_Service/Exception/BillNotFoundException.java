package CRBT.Billing_Service.Exception;

public class BillNotFoundException extends RuntimeException{
	public BillNotFoundException(String message) {
        super(message);
    }

}
