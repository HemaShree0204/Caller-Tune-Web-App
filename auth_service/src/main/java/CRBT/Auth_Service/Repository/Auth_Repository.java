package CRBT.Auth_Service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import CRBT.Auth_Service.Model.AuthUser;

import java.util.Optional;

public interface Auth_Repository extends JpaRepository<AuthUser, Long> {
    Optional<AuthUser> findByUsername(String username);
    
    @Query("SELECT a FROM AuthUser a WHERE a.users_id = :users_id")
    Optional<AuthUser> findByUsers_id(Long users_id);
    boolean existsByUsername(String username);
}