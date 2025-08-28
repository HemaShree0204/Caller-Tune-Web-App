package CRBT.Billing_Service.Model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sub_id;

    @Column(name = "users_id",nullable = false)
    private Long users_id;

    @Column(nullable = false)
    private String planName;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String currency;

    @Column(name = "song_id")
    private Long song_id;

    private LocalDate startDate;
    private LocalDate endDate;

    private Boolean isActive;
    private Boolean autoRenew;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private SubStatus status;

    public enum SubStatus {
        ACTIVE, EXPIRED
    }

	public Long getSub_id() {
		return sub_id;
	}

	public void setSub_id(Long sub_id) {
		this.sub_id = sub_id;
	}

	public Long getUsers_id() {
		return users_id;
	}

	public void setUsers_id(Long users_id) {
		this.users_id = users_id;
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

	public Long getSong_id() {
		return song_id;
	}

	public void setSong_id(Long song_id) {
		this.song_id = song_id;
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

    
}
