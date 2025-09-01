package CRBT.Admin_Service_Controller;

import CRBT.Admin_Service.Controller.Report_Controller;
import CRBT.Admin_Service.Model.AuditLog;
import CRBT.Admin_Service.Service.Report_Service;
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

class Report_ControllerTest {

    private MockMvc mockMvc;

    @Mock
    private Report_Service reportService;

    @InjectMocks
    private Report_Controller reportController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reportController).build();
    }

    @Test
    void testGetAllLogs() throws Exception {
        AuditLog log1 = new AuditLog();
        log1.setId(1L);
        AuditLog log2 = new AuditLog();
        log2.setId(2L);

        when(reportService.getAuditLogs()).thenReturn(Arrays.asList(log1, log2));

        mockMvc.perform(get("/api/reports/logs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testSaveLog() throws Exception {
        AuditLog log = new AuditLog();
        log.setId(1L);

        when(reportService.logAction(any(AuditLog.class))).thenReturn(log);

        mockMvc.perform(post("/api/reports/logs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(log)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
}
