package CRBT.Notifications_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = {
	    org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class,
	    org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration.class
	})
public class NotificationsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationsServiceApplication.class, args);
	}
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
