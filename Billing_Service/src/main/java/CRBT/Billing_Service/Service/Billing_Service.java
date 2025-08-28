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
import CRBT.Billing_Service.Model.Billing;
import CRBT.Billing_Service.Repository.Billing_Repository;

@Service
public class Billing_Service {

    @Autowired
    private Billing_Repository bill_rep;

    @Autowired
    private RestTemplate restTemplate;
    
    public UserDTO getUserById(Long user_id) {
        String url = "http://AUTH-SERVICE/users/" + user_id; // Replace with actual service name or URL
        return restTemplate.getForObject(url, UserDTO.class);
    }


    public Billing_ResponseDTO createBill(Billing_RequestDTO billRequest) {
    	
    	UserDTO user = getUserById(billRequest.getUsers_id());
        if (user == null) {
            throw new RuntimeException("User not found in Auth service");
        }
        Billing bill = new Billing();
        bill.setUsers_id(billRequest.getUsers_id());
        bill.setSub_id(billRequest.getSub_id());
        bill.setSong_id(billRequest.getSong_id());
        bill.setAmount(billRequest.getAmount());
        bill.setStatus(Billing.BillingStatus.PENDING);
        bill.setPay_date(null);

        Billing saved = bill_rep.save(bill);
        return mapToResponse(saved);
    }

    public Billing_ResponseDTO updateBill(Long invoice_id, Billing_RequestDTO update) {
        Billing bill = bill_rep.findById(invoice_id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        bill.setUsers_id(update.getUsers_id());
        bill.setSub_id(update.getSub_id());
        bill.setSong_id(update.getSong_id());
        bill.setAmount(update.getAmount());
        if (update.getStatus() != null) {
            bill.setStatus(Billing.BillingStatus.valueOf(update.getStatus().toUpperCase()));
        }

        Billing updated = bill_rep.save(bill);
        return mapToResponse(updated);
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
        String url = "http://localhost:8080/user/" + username;
        UserDTO user = restTemplate.getForObject(url, UserDTO.class);

        if (user == null || user.getUsers_id() == null) {
            throw new RuntimeException("User not found in Auth Service");
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
