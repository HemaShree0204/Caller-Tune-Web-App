package CRBT.Billing_Service.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import CRBT.Billing_Service.Model.Subscription;
import CRBT.Billing_Service.Repository.Subscription_Repository;

public class Subscription_Service {
	
	@Autowired
	private Subscription_Repository subrepo;

	public List<Subscription> getByUserId(Long user_id) {
		// TODO Auto-generated method stub
		return subrepo.findByUserId(user_id);
	}

	public List<Subscription> getAll(Subscription sub) {
		// TODO Auto-generated method stub
		return subrepo.findAll();
	}

	public List<Subscription> getByUserName(String username) {
		// TODO Auto-generated method stub
		return subrepo.findByUsername(username);
	}

	public Subscription updateSub(Long user_id, Subscription updatedSub) {
		// TODO Auto-generated method stub
		return null;
	}

	public Subscription adminUpdateSub(Long subscriptionId, Subscription updatedSub) {
		// TODO Auto-generated method stub
		return null;
	}

	public Subscription createSub(Subscription sub) {
		// TODO Auto-generated method stub
		return null;
	}

}
