package CRBT.Tunes_Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin("*")
public class TunesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TunesServiceApplication.class, args);
	}
}
