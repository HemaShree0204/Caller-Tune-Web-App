package CRBT.Auth_Service.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import CRBT.Auth_Service.DTO.AuthRegisterRequest;
import CRBT.Auth_Service.DTO.AuthResponse;
import CRBT.Auth_Service.DTO.LoginRequest;
import CRBT.Auth_Service.Model.AuthUser;
import CRBT.Auth_Service.Repository.Auth_Repository;

import java.util.Optional;

@Service
public class Auth_Service {

    @Autowired
    private Auth_Repository authRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    public AuthResponse authenticate(LoginRequest loginRequest) {
        Optional<AuthUser> authUserOpt = authRepository.findByUsername(loginRequest.getUsername());
        
        if (authUserOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        AuthUser authUser = authUserOpt.get();

        if (!passwordEncoder.matches(loginRequest.getPassword(), authUser.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(authUser);
        return new AuthResponse(token, authUser.getUsers_id());
    }

    public boolean validateToken(String token) {
        try {
            return jwtService.validateToken(token);
        } catch (Exception e) {
            return false;
        }
    }

    public AuthResponse refreshToken(String oldToken) {
        if (!validateToken(oldToken)) {
            throw new RuntimeException("Invalid token");
        }
        
        String username = jwtService.extractUsername(oldToken);
        Optional<AuthUser> authUserOpt = authRepository.findByUsername(username);
        
        if (authUserOpt.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        AuthUser authUser = authUserOpt.get();
        String newToken = jwtService.generateToken(authUser);
        
        return new AuthResponse(newToken, authUser.getUsers_id());
    }

	public void registerInternalUser(AuthRegisterRequest request) {
		// TODO Auto-generated method stub
		if (authRepository.existsByUsername(request.getUsername())) {
	        throw new RuntimeException("Username already exists in auth DB");
	    }

	    AuthUser user = new AuthUser();
	    user.setUsername(request.getUsername());
	    user.setPassword(passwordEncoder.encode(request.getPassword()));
	    user.setUsers_id(request.getUsers_id());

	    authRepository.save(user);
	}
}