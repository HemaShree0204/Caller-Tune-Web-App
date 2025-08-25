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
	private Long sub_id;
	public Long getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(Long invoice_id) {
		this.invoice_id = invoice_id;
	}
	public Long getSub_id() {
		return sub_id;
	}
	public void setSub_id(Long sub_id) {
		this.sub_id = sub_id;
	}
	public Long getSong_id() {
		return song_id;
	}
	public void setSong_id(Long song_id) {
		this.song_id = song_id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDateTime getPay_date() {
		return pay_date;
	}
	public void setPay_date(LocalDateTime pay_date) {
		this.pay_date = pay_date;
	}
	public BillingStatus getStatus() {
		return status;
	}
	public void setStatus(BillingStatus status) {
		this.status = status;
	}
	private Long song_id;
	
	
	@Column(nullable=false)
	private double amount;
	
	LocalDateTime pay_date;
	
	public enum BillingStatus {
	    PENDING, SUCCESS, FAILED, CANCELLED, REFUNDED}
	@Enumerated(EnumType.STRING)
	private BillingStatus status;

}
