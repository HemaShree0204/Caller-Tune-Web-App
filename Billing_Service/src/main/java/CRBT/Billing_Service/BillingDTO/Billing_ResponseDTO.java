package CRBT.Billing_Service.BillingDTO;

import java.time.LocalDateTime;

public class Billing_ResponseDTO {
    private Long invoice_id;
    private Long users_id;
    private Long sub_id;
    private Long song_id;
    private double amount;
    private LocalDateTime pay_date;
    private String status;

    public Billing_ResponseDTO(Long invoice_id, Long users_id, Long sub_id, Long song_id,
                               double amount, LocalDateTime pay_date, String status) {
        this.invoice_id = invoice_id;
        this.users_id = users_id;
        this.sub_id = sub_id;
        this.song_id = song_id;
        this.amount = amount;
        this.pay_date = pay_date;
        this.status = status;
    }

    // Getters
    public Long getInvoice_id() { return invoice_id; }
    public Long getUsers_id() { return users_id; }
    public Long getSub_id() { return sub_id; }
    public Long getSong_id() { return song_id; }
    public double getAmount() { return amount; }
    public LocalDateTime getPay_date() { return pay_date; }
    public String getStatus() { return status; }
}
