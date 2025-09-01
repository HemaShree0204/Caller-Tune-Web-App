package CRBT.User_Service.Controller;

import CRBT.User_Service.DTO.UserRegistrationRequest;
import CRBT.User_Service.DTO.UsersDTO;
import CRBT.User_Service.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UsersDTO> registerUser(@RequestBody UserRegistrationRequest request) {
        // Set default role if not provided
        if (request.getRole() == null || request.getRole().isEmpty()) {
            request.setRole("USER");
        }
        
        UsersDTO createdUser = userService.createUser(request);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{users_id}")
    public ResponseEntity<UsersDTO> getUserById(@PathVariable Long users_id) {
        UsersDTO user = userService.getUserById(users_id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UsersDTO> getUserByUsername(@PathVariable String username) {
        UsersDTO user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }
}