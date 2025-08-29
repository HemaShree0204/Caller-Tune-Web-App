package CRBT.Auth_Service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import CRBT.Auth_Service.Model.AuthUser;

import java.util.Optional;

public interface Auth_Repository extends JpaRepository<AuthUser, Long> {
    Optional<AuthUser> findByUsername(String username);
    Optional<AuthUser> findByUserId(Long userId);
    boolean existsByUsername(String username);
}