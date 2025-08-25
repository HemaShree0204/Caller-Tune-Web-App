package CRBT.Billing_Service.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CRBT.Billing_Service.Model.Subscription;
import CRBT.Billing_Service.Service.Subscription_Service;

@RestController
@RequestMapping("/subscriptions")
public class Subscription_Controller {
	
	@Autowired
	private Subscription_Service subservice;
	
	@PostMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public Subscription createSubEntry(@RequestBody Subscription sub)
	{
		return subservice.createSub(sub);
		
	}
	
	@GetMapping("/{user_id}")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Subscription> getByUser(@PathVariable Long user_id)
	{
		return subservice.getByUserId(user_id);
	}
	
	@PutMapping("/admin/update/{subscriptionId}")
	@PreAuthorize("hasRole('ADMIN')")
	public Subscription adminUpdateSubscription(@PathVariable Long subscriptionId, @RequestBody Subscription updatedSub) {
	    return subservice.adminUpdateSub(subscriptionId, updatedSub);
	}

	
	@GetMapping("/user/{username}")
	@PreAuthorize("hasRole('USER')")
	public List<Subscription> getByUserName(@PathVariable String username)
	{
		return subservice.getByUserName(username);
	}
	
	@PutMapping("/user/id/{user_id}")
	@PreAuthorize("hasRole('USER')")
	public Subscription updateSubscription(@PathVariable Long user_id, @RequestBody Subscription updatedSub)
	{
		return subservice.updateSub(user_id, updatedSub);
	}
	
}
