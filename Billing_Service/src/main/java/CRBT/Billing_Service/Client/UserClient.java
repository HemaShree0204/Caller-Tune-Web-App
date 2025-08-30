package CRBT.Billing_Service.Client;

import CRBT.Billing_Service.BillingDTO.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth-service", url = "http://localhost:8080")  // Matches the service ID registered with Eureka
public interface UserClient {

    @GetMapping("/users/{users_id}")
    UserDTO getUserById(@PathVariable("users_id") Long users_id);
    

    @GetMapping("/users/username/{username}")
    UserDTO getUserByUsername(@PathVariable("username") String username);
}
