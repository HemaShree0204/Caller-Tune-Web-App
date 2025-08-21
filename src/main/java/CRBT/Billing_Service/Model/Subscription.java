package CRBT.Billing_Service.Model;

import java.time.LocalDateTime;

import CRBT.Billing_Service.Model.Billing.BillingStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Subscription {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long sub_id;
	private Long user_id;
	private String plan_type;
	LocalDateTime start_date;
	LocalDateTime end_date;
	
	public enum SubStatus {
	    ACTIVE,EXPIRED}
	@Enumerated(EnumType.STRING)
	private SubStatus status;

}
