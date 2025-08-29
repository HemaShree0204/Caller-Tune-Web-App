package CRBT.Tunes_Service.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())       // disable CSRF for testing
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()   // all endpoints require authentication
            )
            .httpBasic();                        // enable Basic Auth

        return http.build();
    }
}
