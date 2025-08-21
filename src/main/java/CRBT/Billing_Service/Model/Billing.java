package CRBT.Billing_Service.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "billing")
public class Billing {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long invoice_id;
	private Long user_id;
	private Long song_id;
	
	@Column(nullable=false)
	private double amount;
	
	LocalDateTime startdate;
	LocalDateTime enddate;
	
	public enum BillingStatus {
	    PENDING, SUCCESS, FAILED, CANCELLED, REFUNDED}
	@Enumerated(EnumType.STRING)
	private BillingStatus status;

}
