package CRBT.User_Service.Service;

import org.springframework.web.client.RestTemplate;
import CRBT.User_Service.DTO.AuthRegisterRequest;
import CRBT.User_Service.DTO.UserRegistrationRequest;
import CRBT.User_Service.DTO.UsersDTO;
import CRBT.User_Service.Model.Users;
import CRBT.User_Service.Repository.UserRepository;
import CRBT.User_Service.Exceptions.UserNotFoundException;
import CRBT.User_Service.Exceptions.DuplicateUsernameException;
import CRBT.User_Service.Exceptions.DuplicateMobileNumberException;

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
            throw new DuplicateUsernameException("Username already exists");
        }

        // Check if mobile number already exists
        if (userRepository.existsByMobileNumber(request.getMobileNumber())) {
            throw new DuplicateMobileNumberException("Mobile number already exists");
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
        user.setMobileNumber(request.getMobileNumber());
        user.setRole(role);
        user.setPlanType(request.getPlanType());

        // Save user
        Users savedUser = userRepository.save(user);

        // Set default caller tune (pick one of your uploaded tunes)
        savedUser.setCallerTune("1756462923780_Enna_Sona_M.mp3");
        userRepository.save(savedUser);

        // Register user in Auth Service
        AuthRegisterRequest authRequest = new AuthRegisterRequest(
            request.getUsername(),
            request.getPassword(),
            savedUser.getUsers_id()
        );

        try {
            restTemplate.postForEntity(
                "http://localhost:8082/auth/internal-register",
                authRequest,
                Void.class
            );
        } catch (Exception e) {
            // rollback if Auth Service fails
            userRepository.deleteById(savedUser.getUsers_id());
            throw new RuntimeException("Failed to register with Auth Service: " + e.getMessage());
        }

        return convertToDTO(savedUser);
    }

    public UsersDTO getUserById(Long users_id) {
        Users user = userRepository.findById(users_id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return convertToDTO(user);
    }

    public UsersDTO getUserByUsername(String username) {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return convertToDTO(user);
    }

    private UsersDTO convertToDTO(Users user) {
        UsersDTO dto = new UsersDTO();
        dto.setUsers_id(user.getUsers_id());
        dto.setUsername(user.getUsername());
        dto.setMobileNumber(user.getMobileNumber());
        dto.setCallerTune(user.getCallerTune());
        dto.setRole(user.getRole().name());
        dto.setPlan_type(user.getPlanType());
        return dto;
    }
}
