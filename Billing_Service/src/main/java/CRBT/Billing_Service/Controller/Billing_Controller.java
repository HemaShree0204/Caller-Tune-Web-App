package CRBT.Billing_Service.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import CRBT.Billing_Service.BillingDTO.Billing_RequestDTO;
import CRBT.Billing_Service.BillingDTO.Billing_ResponseDTO;
import CRBT.Billing_Service.Model.Billing;
import CRBT.Billing_Service.Service.Billing_Service;

@RestController
@RequestMapping("/billing")
public class Billing_Controller {

    @Autowired
    private Billing_Service service;

    // ✅ 1. Create a new bill
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Billing_ResponseDTO> addBillEntry(@RequestBody Billing_RequestDTO billRequest) {
        Billing_ResponseDTO response = service.createBill(billRequest);
        return ResponseEntity.ok(response);
    }

    // ✅ 2. Get all bills (for admin)
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Billing>> getAllBill() {
        return ResponseEntity.ok(service.getAll());
    }

    // ✅ 3. Get bill by invoice ID
    @GetMapping("/{invoice_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Billing_ResponseDTO> getBillById(@PathVariable Long invoice_id) {
        return service.getById(invoice_id)
                .map(bill -> ResponseEntity.ok(service.mapToResponse(bill)))
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ 4. Get bills by user ID (admin)
    @GetMapping("/user/{user_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Billing_ResponseDTO>> getBillByUser(@PathVariable Long users_id) {
        return ResponseEntity.ok(service.getByUser(users_id));
    }

    // ✅ 5. Update bill
    @PutMapping("/{invoice_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Billing_ResponseDTO> updateInvoice(@PathVariable Long invoice_id,
                                                             @RequestBody Billing_RequestDTO update) {
        return ResponseEntity.ok(service.updateBill(invoice_id, update));
    }

    // ✅ 6. Delete bill
    @DeleteMapping("/{invoice_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBill(@PathVariable Long invoice_id) {
        service.deleteById(invoice_id);
        return ResponseEntity.noContent().build();
    }

    // ✅ 7. Simulate auto payment
    @PostMapping("/simulate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Billing> simulateBill(@RequestBody Billing bill) {
        Billing simulated = service.simulatePayment(bill);
        return ResponseEntity.ok(simulated);
    }

    // ✅ 8. Manual payment (user/admin)
    @PostMapping("/{invoice_id}/pay")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Billing> simulateManualPayment(
            @PathVariable Long invoice_id,
            @RequestParam(defaultValue = "SUCCESS") String status) {
        try {
            Billing bill = service.simulateManualPayment(invoice_id, status);
            return ResponseEntity.ok(bill);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); // Invalid status
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Bill not found
        }
    }

    // ✅ 9. Get bills for authenticated user
    @GetMapping("/user/billing")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Billing>> getUserBills(Authentication authentication) {
        String username = authentication.getName();
        List<Billing> bills = service.getBillsForUser(username);
        return ResponseEntity.ok(bills);
    }
}
