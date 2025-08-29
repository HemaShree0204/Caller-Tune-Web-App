package CRBT.Auth_Service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import CRBT.Auth_Service.DTO.AuthRegisterRequest;
import CRBT.Auth_Service.DTO.AuthResponse;
import CRBT.Auth_Service.DTO.LoginRequest;
import CRBT.Auth_Service.Service.Auth_Service;

@RestController
@RequestMapping("/auth")
public class Auth_Controller {

    @Autowired
    private Auth_Service authService;
    
    @PostMapping("/internal-register")
    public ResponseEntity<Void> registerInternal(@RequestBody AuthRegisterRequest request) {
        authService.registerInternalUser(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse response = authService.authenticate(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String token) {
        // Remove "Bearer " prefix if present
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        boolean isValid = authService.validateToken(token);
        return ResponseEntity.ok(isValid);
    }

    // Optional: Token refresh endpoint
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestHeader("Authorization") String oldToken) {
        if (oldToken != null && oldToken.startsWith("Bearer ")) {
            oldToken = oldToken.substring(7);
        }
        AuthResponse response = authService.refreshToken(oldToken);
        return ResponseEntity.ok(response);
    }
}