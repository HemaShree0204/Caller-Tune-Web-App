package CRBT.Admin_Service.dto;

public class SubscriptionResponse {
    private Long id;
    private Long userId;
    private String planName;
    private String status;

    public SubscriptionResponse() {}

    public SubscriptionResponse(Long id, Long userId, String planName, String status) {
        this.id = id;
        this.userId = userId;
        this.planName = planName;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
