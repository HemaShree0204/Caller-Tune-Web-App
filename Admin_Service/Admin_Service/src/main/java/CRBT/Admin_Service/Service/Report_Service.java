package CRBT.Admin_Service.Service;

import CRBT.Admin_Service.Repository.AuditLogRepository;
import CRBT.Admin_Service.Model.AuditLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Report_Service {

    @Autowired
    private AuditLogRepository auditLogRepository;

    // Get all audit logs
    public List<AuditLog> getAuditLogs() {
        return auditLogRepository.findAll();
    }

    // Save a log
    public AuditLog logAction(AuditLog log) {
        return auditLogRepository.save(log);
    }
}
