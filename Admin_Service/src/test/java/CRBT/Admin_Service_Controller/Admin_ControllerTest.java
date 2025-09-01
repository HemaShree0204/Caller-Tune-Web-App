package CRBT.Admin_Service_Controller;

import CRBT.Admin_Service.Controller.Admin_Controller;
import CRBT.Admin_Service.Model.Admin;
import CRBT.Admin_Service.Service.Admin_Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class Admin_ControllerTest {

    private MockMvc mockMvc;

    @Mock
    private Admin_Service adminService;

    @InjectMocks
    private Admin_Controller adminController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    @Test
    void testGetAllAdmins() throws Exception {
        Admin admin1 = new Admin();
        admin1.setId(1L);
        admin1.setUsername("admin1");
        Admin admin2 = new Admin();
        admin2.setId(2L);
        admin2.setUsername("admin2");

        when(adminService.getAllAdmins()).thenReturn(Arrays.asList(admin1, admin2));

        mockMvc.perform(get("/api/admins"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].username").value("admin1"));

        verify(adminService, times(1)).getAllAdmins();
    }

    @Test
    void testGetAdminById() throws Exception {
        Admin admin = new Admin();
        admin.setId(1L);
        admin.setUsername("admin1");

        when(adminService.getAdminById(1L)).thenReturn(admin);

        mockMvc.perform(get("/api/admins/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("admin1"));
    }

    @Test
    void testCreateAdmin() throws Exception {
        Admin admin = new Admin();
        admin.setUsername("newAdmin");

        when(adminService.createAdmin(any(Admin.class))).thenReturn(admin);

        mockMvc.perform(post("/api/admins")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(admin)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("newAdmin"));
    }

    @Test
    void testUpdateAdmin() throws Exception {
        Admin admin = new Admin();
        admin.setUsername("updatedAdmin");

        when(adminService.updateAdmin(eq(1L), any(Admin.class))).thenReturn(admin);

        mockMvc.perform(put("/api/admins/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(admin)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("updatedAdmin"));
    }

    @Test
    void testDeleteAdmin() throws Exception {
        doNothing().when(adminService).deleteAdmin(1L);

        mockMvc.perform(delete("/api/admins/1"))
                .andExpect(status().isNoContent());

        verify(adminService, times(1)).deleteAdmin(1L);
    }
}
