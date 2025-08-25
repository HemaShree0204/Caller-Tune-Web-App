package CRBT.Billing_Service.Model;

import java.time.LocalDate;
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
@Table(name="subscription")
public class Subscription {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long sub_id;
	@Column(nullable = false)
    private Long user_id;

    @Column(nullable = false)
    private String planName; // e.g., "Monthly", "Premium"

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String currency;

    private LocalDate startDate;
    public Long getSub_id() {
		return sub_id;
	}
	public void setSub_id(Long sub_id) {
		this.sub_id = sub_id;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Boolean getAutoRenew() {
		return autoRenew;
	}
	public void setAutoRenew(Boolean autoRenew) {
		this.autoRenew = autoRenew;
	}
	public Long getCallerTuneId() {
		return callerTuneId;
	}
	public void setCallerTuneId(Long callerTuneId) {
		this.callerTuneId = callerTuneId;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public SubStatus getStatus() {
		return status;
	}
	public void setStatus(SubStatus status) {
		this.status = status;
	}
	private LocalDate endDate;

    private Boolean isActive;
    private Boolean autoRenew;

    private Long callerTuneId; // Optional: ID of selected tune

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

	
	public enum SubStatus {
	    ACTIVE,EXPIRED}
	@Enumerated(EnumType.STRING)
	private SubStatus status;

}
