package CRBT.Billing_Service.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import CRBT.Billing_Service.BillingDTO.Billing_RequestDTO;
import CRBT.Billing_Service.BillingDTO.Billing_ResponseDTO;
import CRBT.Billing_Service.BillingDTO.UserDTO;
import CRBT.Billing_Service.Client.UserClient;
import CRBT.Billing_Service.Model.Billing;
import CRBT.Billing_Service.Repository.Billing_Repository;

@Service
public class Billing_Service {

    @Autowired
    private Billing_Repository bill_rep;

    @Autowired
    private UserClient userClient;
    
    public UserDTO getUserById(Long users_id) {
    	return userClient.getUserById(users_id);
    }


    public Billing_ResponseDTO createBill(Billing_RequestDTO billRequest) {
    	UserDTO user = userClient.getUserById(billRequest.getUsers_id());
        if (user == null) {
            throw new RuntimeException("User not found in User service");
        }

        Billing bill = new Billing();
        bill.setUsers_id(billRequest.getUsers_id());
        bill.setSub_id(billRequest.getSub_id());
        bill.setSong_id(billRequest.getSong_id());
        bill.setAmount(billRequest.getAmount());
        bill.setStatus(billRequest.getStatus() == null
                ? Billing.BillingStatus.PENDING
                : Billing.BillingStatus.valueOf(billRequest.getStatus().toUpperCase()));
        bill.setPay_date(null);

        return mapToResponse(bill_rep.save(bill));
    }

    public Billing_ResponseDTO updateBill(Long invoice_id, Billing_RequestDTO update) {
        Billing bill = bill_rep.findById(invoice_id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        if (update.getUsers_id() != null) bill.setUsers_id(update.getUsers_id());
        if (update.getSub_id() != null) bill.setSub_id(update.getSub_id());
        if (update.getSong_id() != null) bill.setSong_id(update.getSong_id());
        if (update.getAmount() > 0) bill.setAmount(update.getAmount());
        if (update.getStatus() != null) {
            bill.setStatus(Billing.BillingStatus.valueOf(update.getStatus().toUpperCase()));
        }
        return mapToResponse(bill_rep.save(bill));
        }

    public Optional<Billing> getById(Long invoice_id) {
        return bill_rep.findById(invoice_id);
    }

    public List<Billing_ResponseDTO> getByUser(Long users_id) {
        List<Billing> bills = bill_rep.findByUsers_id(users_id);
        return bills.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    public List<Billing> getAll() {
        return bill_rep.findAll();
    }

    public void deleteById(Long invoice_id) {
        bill_rep.deleteById(invoice_id);
    }

    public List<Billing> getBillsForUser(String username) {
    	UserDTO user = userClient.getUserByUsername(username);

        if (user == null || user.getUsers_id() == null) {
            throw new RuntimeException("User not found in User Service");
        }

        return bill_rep.findByUsers_id(user.getUsers_id());
    }

    public Billing simulatePayment(Billing bill) {
        bill.setPay_date(LocalDateTime.now());
        bill.setStatus(Math.random() > 0.2 ? Billing.BillingStatus.SUCCESS : Billing.BillingStatus.FAILED);
        return bill_rep.save(bill);
    }

    public Billing simulateManualPayment(Long invoice_id, String status) {
        Billing bill = bill_rep.findById(invoice_id)
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        if (bill.getStatus() != Billing.BillingStatus.PENDING) {
            throw new RuntimeException("Cannot pay a non-pending bill");
        }

        Billing.BillingStatus chosenStatus;
        try {
            chosenStatus = Billing.BillingStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid billing status");
        }

        bill.setStatus(chosenStatus);
        bill.setPay_date(LocalDateTime.now());
        return bill_rep.save(bill);
    }

    public Billing_ResponseDTO mapToResponse(Billing bill) {
        return new Billing_ResponseDTO(
                bill.getInvoice_id(),
                bill.getUsers_id(),
                bill.getSub_id(),
                bill.getSong_id(),
                bill.getAmount(),
                bill.getPay_date(),
                bill.getStatus().name()
        );
    }
}
