package CRBT.Auth_Service.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", url = "http://localhost:8080/users")
public interface UserClient {
    @PutMapping("/{id}/mobile")
    void updateUserMobile(@PathVariable("id") Long id, @RequestBody String mobile);
}
