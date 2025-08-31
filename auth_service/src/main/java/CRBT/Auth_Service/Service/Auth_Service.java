package CRBT.Auth_Service.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import CRBT.Auth_Service.Client.NotificationClient;
import CRBT.Auth_Service.Client.UserClient;
import CRBT.Auth_Service.DTO.AuthRegisterRequest;
import CRBT.Auth_Service.DTO.AuthResponse;
import CRBT.Auth_Service.DTO.LoginRequest;
import CRBT.Auth_Service.DTO.OtpRequestDto;
import CRBT.Auth_Service.DTO.OtpVerifyDto;
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

    @Autowired
    private UserClient userClient;
    
    @Autowired
    private NotificationClient notificationClient;

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
    
    public String loginWithOtp(String username, String password) {
        AuthUser user = authRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        // Call Notification Service to send OTP
        OtpRequestDto dto = new OtpRequestDto();
        dto.setPhoneNumber(user.getMobile()); // use correct entity field
        notificationClient.sendOtp(dto);

        return "OTP sent successfully to " + user.getMobile();
    }

    // 🔹 OTP verification (after user enters the code)
    public AuthResponse verifyOtp(String username, String otp) {
    	AuthUser user = authRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Build DTO
        OtpVerifyDto dto = new OtpVerifyDto(user.getMobile(), otp);

        String response = notificationClient.verifyOtp(dto); // returns String message

        if (!response.contains("success")) {  // crude check, better if Notification returns a structured object
            throw new RuntimeException("Invalid OTP");
        }

        // If OTP is valid, generate JWT
        String token = jwtService.generateToken(user);
        return new AuthResponse(token, user.getUsers_id());
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
        if (authRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists in auth DB");
        }

        // Save in Auth DB
        AuthUser user = new AuthUser();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUsers_id(request.getUsers_id());
        authRepository.save(user);

        // Call User Service to store mobile
        userClient.updateUserMobile(request.getUsers_id(), request.getMobileNumber());
    }
}