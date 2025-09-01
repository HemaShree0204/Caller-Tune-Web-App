package CRBT.Admin_Service_Service;

import CRBT.Admin_Service.Model.Admin;
import CRBT.Admin_Service.Repository.AdminRepository;
import CRBT.Admin_Service.Service.Admin_Service;
import CRBT.Admin_Service.exception.AdminNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class Admin_ServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private Admin_Service adminService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAdmins() {
        Admin admin1 = new Admin();
        Admin admin2 = new Admin();
        when(adminRepository.findAll()).thenReturn(Arrays.asList(admin1, admin2));

        List<Admin> result = adminService.getAllAdmins();
        assertEquals(2, result.size());
    }

    @Test
    void testGetAdminById_Success() {
        Admin admin = new Admin();
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));

        Admin result = adminService.getAdminById(1L);
        assertNotNull(result);
    }

    @Test
    void testGetAdminById_NotFound() {
        when(adminRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(AdminNotFoundException.class, () -> adminService.getAdminById(1L));
    }

    @Test
    void testCreateAdmin() {
        Admin admin = new Admin();
        when(adminRepository.save(admin)).thenReturn(admin);

        Admin result = adminService.createAdmin(admin);
        assertNotNull(result);
    }
}

