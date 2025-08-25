package CRBT.Billing_Service.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import CRBT.Billing_Service.Model.Billing;

public interface Billing_Repository extends JpaRepository<Billing,Long>{

	List<Billing> findByUserId(Long user_id);


}
