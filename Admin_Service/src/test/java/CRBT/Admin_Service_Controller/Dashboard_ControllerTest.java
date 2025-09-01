package CRBT.Admin_Service_Controller;



import CRBT.Admin_Service.Controller.Dashboard_Controller;
import CRBT.Admin_Service.Service.Dashboard_Service;
import CRBT.Admin_Service.dto.StatsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class Dashboard_ControllerTest {

    private MockMvc mockMvc;

    @Mock
    private Dashboard_Service dashboardService;

    @InjectMocks
    private Dashboard_Controller dashboardController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(dashboardController).build();
    }

    @Test
    void testGetQuickStats() throws Exception {
        StatsResponse stats = new StatsResponse(100, 50, 20);
        when(dashboardService.getQuickStats()).thenReturn(stats);

        mockMvc.perform(get("/api/dashboard/stats")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalUsers").value(100))
                .andExpect(jsonPath("$.totalTunes").value(50))
                .andExpect(jsonPath("$.totalSubscriptions").value(20));
    }
}
