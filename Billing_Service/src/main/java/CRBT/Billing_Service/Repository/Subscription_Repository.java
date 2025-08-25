package CRBT.Billing_Service.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import CRBT.Billing_Service.Model.Subscription;

public interface Subscription_Repository extends JpaRepository<Subscription,Long>{

	List<Subscription> findByUsername(String username);

	List<Subscription> findByUserId(Long user_id);

}
