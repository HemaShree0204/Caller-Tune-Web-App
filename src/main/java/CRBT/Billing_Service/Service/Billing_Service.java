package CRBT.Billing_Service.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import CRBT.Billing_Service.Model.Billing;
import CRBT.Billing_Service.Repository.Billing_Repository;

@Service
public class Billing_Service {
	@Autowired
	private Billing_Repository bill_rep;

	public List<Billing> getAll(Billing bill) {
		// TODO Auto-generated method stub
		return bill_rep.findAll();
	}

	public Optional<Billing> getById(Long invoice_id) {
		// TODO Auto-generated method stub
		return bill_rep.findById(invoice_id);
	}

	public Optional<Billing> getByUser(Long user_id) {
		// TODO Auto-generated method stub
		return bill_rep.findById(user_id);
	}

	public void deleteById(Long invoice_id) {
		// TODO Auto-generated method stub
		bill_rep.deleteById(invoice_id);
	}

	public List<Billing> updateBillById(Long invoice_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
