package CRBT.Billing_Service.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import CRBT.Billing_Service.Model.Billing;
import CRBT.Billing_Service.Service.Billing_Service;

@RestController
public class Billing_Controller {
	
	@Autowired
	private Billing_Service service;
	
	@PostMapping("/billing")
	public List<Billing> addBillEntry(@RequestBody Billing bill)
	{
		return service.getAll(bill);
		
	}
	
	@GetMapping("/{invoice_id}")
	public Optional<Billing> getBillById(@PathVariable Long invoice_id)
	{
		return service.getById(invoice_id);
		
	}
	
	@GetMapping("/{user_id}")
	public Optional<Billing> getBillByUser(@PathVariable Long user_id)
	{
		return service.getByUser(user_id);
	}
	
	@PutMapping("/{invoice_id}")
	public List<Billing> updateInvoice(@PathVariable Long invoice_id)
	{
		return service.updateBillById(invoice_id);
	}
	
	@DeleteMapping("/{invoice_id}")
	public void deleteBill(@PathVariable Long invoice_id)
	{
		service.deleteById(invoice_id);
	}

}
