package CRBT.Billing_Service.BillingDTO;

import java.time.LocalDateTime;

public class Billing_ResponseDTO {
	private Long invoiceId;
    private Long userId;
    private double amount;
    private String status;
    private LocalDateTime pay_date;


}
