package CRBT.Billing_Service.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import CRBT.Billing_Service.Model.Billing;

public interface Billing_Repository extends JpaRepository<Billing,Long>{

	@Query("SELECT b FROM Billing b WHERE b.users_id = :users_id")
	List<Billing> findByUsers_id(@Param("users_id") Long users_id);


}
