package CRBT.Admin_Service_Repository;


import CRBT.Admin_Service.Model.AuditLog;
import CRBT.Admin_Service.Repository.AuditLogRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AuditLogRepositoryTest {

    @Autowired
    private AuditLogRepository auditLogRepository;

    @Test
    void testSaveAndFindAll() {
        AuditLog log = new AuditLog();
        log.setAction("LOGIN");
        log.setPerformedBy("admin1");

        auditLogRepository.save(log);

        assertFalse(auditLogRepository.findAll().isEmpty());
    }
}

