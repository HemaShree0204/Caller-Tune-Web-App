package CRBT.Admin_Service.Repository;

import CRBT.Admin_Service.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    // Find by username
    Optional<Admin> findByUsername(String username);

    // Find by email
    Optional<Admin> findByEmail(String email);

    // Check if username already exists
    boolean existsByUsername(String username);

    // Check if email already exists
    boolean existsByEmail(String email);
}
