package CRBT.Billing_Service.BillingDTO;

public class Billing_RequestDTO {
    private Long users_id;
    private Long sub_id;
    private Long song_id;
    private double amount;
    private String status; // Optional: default is PENDING

    // Getters and Setters
    public Long getUsers_id() { return users_id; }
    public void setUsers_id(Long users_id) { this.users_id = users_id; }

    public Long getSub_id() { return sub_id; }
    public void setSub_id(Long sub_id) { this.sub_id = sub_id; }

    public Long getSong_id() { return song_id; }
    public void setSong_id(Long song_id) { this.song_id = song_id; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
