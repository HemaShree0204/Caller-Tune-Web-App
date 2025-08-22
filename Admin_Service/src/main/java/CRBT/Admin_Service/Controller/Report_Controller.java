package CRBT.Admin_Service.Controller;

import CRBT.Admin_Service.Model.AuditLog;
import CRBT.Admin_Service.Service.Report_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/reports")
public class Report_Controller {

    @Autowired
    private Report_Service reportService;

    // Get all logs
    @GetMapping("/logs")
    public ResponseEntity<List<AuditLog>> getAllLogs() {
        return ResponseEntity.ok(reportService.getAuditLogs());
    }

    // Save a new log (optional API for testing)
    @PostMapping("/logs")
    public ResponseEntity<AuditLog> saveLog(@RequestBody AuditLog log) {
        return ResponseEntity.ok(reportService.logAction(log));
    }
}
