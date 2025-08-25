package CRBT.Billing_Service.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import CRBT.Billing_Service.BillingDTO.UserDTO;
import CRBT.Billing_Service.Model.Billing;
import CRBT.Billing_Service.Repository.Billing_Repository;

@Service
public class Billing_Service {
	
	@Autowired
	private Billing_Repository bill_rep;
	private RestTemplate restTemplate;
	
	public UserDTO getUserInfo(String username) {
	    String url = "http://localhost:8080/user/" + username; // Replace with actual URL
	    return restTemplate.getForObject(url, UserDTO.class);
	}


	//Admin features
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

	//User features
	public List<Billing> getBillsForUser(String username) {
		// TODO Auto-generated method stub
		String url = "http://localhost:8080/user/" + username; // Replace with actual Auth Service URL
	    UserDTO user = restTemplate.getForObject(url, UserDTO.class);

	    if (user == null || user.getUser_id() == null) {
	        throw new RuntimeException("User not found in Auth Service");
	    }

	    // Use userId to fetch billing records
	    return bill_rep.findByUserId(user.getUser_id());
	}
}
