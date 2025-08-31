package CRBT.User_Service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import CRBT.User_Service.Model.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByMobileNumber(String mobileNumber);
}
