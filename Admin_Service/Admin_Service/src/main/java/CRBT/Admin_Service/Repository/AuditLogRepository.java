package CRBT.Admin_Service.Repository;

import CRBT.Admin_Service.Model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    // You can add custom queries later if needed (like filtering by date, action, etc.)
}
