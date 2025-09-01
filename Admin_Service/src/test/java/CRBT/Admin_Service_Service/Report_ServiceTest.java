package CRBT.Admin_Service_Service;

import CRBT.Admin_Service.Model.AuditLog;
import CRBT.Admin_Service.Repository.AuditLogRepository;
import CRBT.Admin_Service.Service.Report_Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class Report_ServiceTest {

    @Mock
    private AuditLogRepository auditLogRepository;

    @InjectMocks
    private Report_Service reportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAuditLogs() {
        AuditLog log1 = new AuditLog();
        AuditLog log2 = new AuditLog();
        when(auditLogRepository.findAll()).thenReturn(Arrays.asList(log1, log2));

        List<AuditLog> logs = reportService.getAuditLogs();
        assertEquals(2, logs.size());
    }

    @Test
    void testLogAction() {
        AuditLog log = new AuditLog();
        when(auditLogRepository.save(log)).thenReturn(log);

        AuditLog result = reportService.logAction(log);
        assertNotNull(result);
    }
}
