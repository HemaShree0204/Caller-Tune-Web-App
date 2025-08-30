package CRBT.Billing_Service.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import CRBT.Billing_Service.Model.Subscription;

public interface Subscription_Repository extends JpaRepository<Subscription,Long>{
	
	@Query("SELECT s FROM Subscription s WHERE s.users_id = :users_id")
	List<Subscription> findByUsers_id(@Param("users_id") Long users_id);

}
