package CRBT.User_Service.Service;

import org.springframework.web.client.RestTemplate;
import CRBT.User_Service.DTO.AuthRegisterRequest;
import CRBT.User_Service.DTO.UserRegistrationRequest;
import CRBT.User_Service.DTO.UsersDTO;
import CRBT.User_Service.Model.Users;
import CRBT.User_Service.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    public UsersDTO createUser(UserRegistrationRequest request) {
        // Check if username already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        
        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Check if mobile number already exists
        if (userRepository.existsByMobileNumber(request.getMobileNumber())) {
            throw new RuntimeException("Mobile number already exists");
        }
        
        // Convert role string to enum
        Users.Role role;
        try {
            role = Users.Role.valueOf(request.getRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            role = Users.Role.USER; // Default to USER
        }
        
        // Create user entity
        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setMobileNumber(request.getMobileNumber()); // <-- Added mobile number
        user.setRole(role);
        user.setPlanType(request.getPlan_type());
        
        // Save user
        Users savedUser = userRepository.save(user);

        // Set default caller tune (pick one of your uploaded tunes)
        savedUser.setCallerTune("1756462923780_Enna_Sona_M.mp3");  // replace with actual filename
        userRepository.save(savedUser); // save again to update caller tune

        AuthRegisterRequest authRequest = new AuthRegisterRequest(
            request.getUsername(),
            request.getPassword(),
            savedUser.getUsers_id()
        );

        // Call Auth Service
        try {
            restTemplate.postForEntity(
                "http://localhost:8082/auth/internal-register", 
                authRequest,
                Void.class
            );
        } catch (Exception e) {
            // Optional: rollback
            userRepository.deleteById(savedUser.getUsers_id());
            throw new RuntimeException("Failed to register with Auth Service: " + e.getMessage());
        }

        // Convert to DTO and return
        return convertToDTO(savedUser);
    }
    
    public UsersDTO getUserById(Long userId) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDTO(user);
    }
    
    public UsersDTO getUserByUsername(String username) {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToDTO(user);
    }
    
    private UsersDTO convertToDTO(Users user) {
        UsersDTO dto = new UsersDTO();
        dto.setUsers_id(user.getUsers_id());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setMobileNumber(user.getMobileNumber()); // <-- Include mobile number in DTO
        dto.setCallerTune(user.getCallerTune());     // <-- Include caller tune in DTO
        dto.setRole(user.getRole().name());
        dto.setPlan_type(user.getPlanType());
        return dto;
    }
}
