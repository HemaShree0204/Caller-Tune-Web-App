package CRBT.User_Service.user_service.Service;

import CRBT.User_Service.DTO.UserRegistrationRequest;
import CRBT.User_Service.DTO.UsersDTO;
import CRBT.User_Service.Model.Users;
import CRBT.User_Service.Repository.UserRepository;
import CRBT.User_Service.Service.UserService;
import CRBT.User_Service.Exceptions.UserNotFoundException;
import CRBT.User_Service.Exceptions.DuplicateUsernameException;
import CRBT.User_Service.Exceptions.DuplicateMobileNumberException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        System.out.println("Initialized mocks for UserServiceTest");
    }

    // =================== Success Cases ===================

    @Test
    void testGetUserById_Success() {
    	System.out.println("Running testGetUserById_Success...");

        Users user = new Users();
        user.setUsers_id(1L);
        user.setUsername("testuser");
        user.setMobileNumber("1234567890");
        user.setRole(Users.Role.USER);
        user.setPlanType("PREMIUM");
        user.setCallerTune("1756462923780_Enna_Sona_M.mp3");  // default caller tune

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UsersDTO dto = userService.getUserById(1L);

        assertEquals("testuser", dto.getUsername());
        assertEquals("USER", dto.getRole());
        System.out.println("testGetUserById_Success completed.");
    }

    @Test
    void testGetUserByUsername_Success() {
    	System.out.println("Running testGetUserByUsername_Success...");

        Users user = new Users();
        user.setUsers_id(2L);
        user.setUsername("john");
        user.setMobileNumber("9876543210");
        user.setRole(Users.Role.USER);
        user.setPlanType("BASIC");
        user.setCallerTune("1756462923780_Enna_Sona_M.mp3");

        when(userRepository.findByUsername("john")).thenReturn(Optional.of(user));

        UsersDTO dto = userService.getUserByUsername("john");

        assertEquals("john", dto.getUsername());
        assertEquals("USER", dto.getRole());
        System.out.println("testGetUserByUsername_Success completed.");
    }

    // =================== Failure Cases ===================

    @Test
    void testGetUserById_NotFound() {
        System.out.println("Running testGetUserById_NotFound...");

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(
                UserNotFoundException.class,
                () -> userService.getUserById(1L)
        );

        assertEquals("User not found", exception.getMessage());
        System.out.println("testGetUserById_NotFound completed.");
    }

    @Test
    void testGetUserByUsername_NotFound() {
        System.out.println("Running testGetUserByUsername_NotFound...");

        when(userRepository.findByUsername("unknown")).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(
                UserNotFoundException.class,
                () -> userService.getUserByUsername("unknown")
        );

        assertEquals("User not found", exception.getMessage());
        System.out.println("testGetUserByUsername_NotFound completed.");
    }

    @Test
    void testCreateUser_DuplicateUsername() {
        System.out.println("Running testCreateUser_DuplicateUsername...");

        when(userRepository.existsByUsername("testuser")).thenReturn(true);

        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setUsername("testuser");
        request.setMobileNumber("1234567890");
        request.setPassword("pass");

        assertThrows(DuplicateUsernameException.class, () -> userService.createUser(request));
        System.out.println("testCreateUser_DuplicateUsername completed.");
    }

    @Test
    void testCreateUser_DuplicateMobileNumber() {
        System.out.println("Running testCreateUser_DuplicateMobileNumber...");

        when(userRepository.existsByUsername("newuser")).thenReturn(false);
        when(userRepository.existsByMobileNumber("1234567890")).thenReturn(true);

        UserRegistrationRequest request = new UserRegistrationRequest();
        request.setUsername("newuser");
        request.setMobileNumber("1234567890");
        request.setPassword("pass");

        assertThrows(DuplicateMobileNumberException.class, () -> userService.createUser(request));
        System.out.println("testCreateUser_DuplicateMobileNumber completed.");
    }
}
