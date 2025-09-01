package CRBT.User_Service.user_service.Controller;


import CRBT.User_Service.Controller.UserController;
import CRBT.User_Service.DTO.UserRegistrationRequest;
import CRBT.User_Service.DTO.UsersDTO;
import CRBT.User_Service.Service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
        System.out.println("Initialized mocks for UserControllerTest");
    }

    @Test
    void testRegisterUser() {
        System.out.println("Running testRegisterUser...");

        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setUsername("testuser");
        request.setPassword("password");
        request.setMobileNumber("9999999999");
        request.setRole("USER");

        UsersDTO dto = new UsersDTO();
        dto.setUsers_id(1L);
        dto.setUsername("testuser");
        dto.setRole("USER");

        when(userService.createUser(request)).thenReturn(dto);

        ResponseEntity<UsersDTO> response = userController.registerUser(request);

        assertEquals(1L, response.getBody().getUsers_id());
        assertEquals("testuser", response.getBody().getUsername());

        System.out.println("Controller returned user ID: " + response.getBody().getUsers_id());
        System.out.println("Controller returned username: " + response.getBody().getUsername());

        System.out.println("testRegisterUser completed.");
    }
}
