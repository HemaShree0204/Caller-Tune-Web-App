package CRBT.Auth_Service.auth_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EntityScan("CRBT.Auth_Service.Model")
@EnableJpaRepositories("CRBT.Auth_Service.Repository")
@EnableFeignClients(basePackages = "CRBT.Auth_Service.Client") 
@CrossOrigin("*")
public class AuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}
	

}
