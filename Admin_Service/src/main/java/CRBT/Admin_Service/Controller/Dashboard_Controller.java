package CRBT.Admin_Service.Controller;

import CRBT.Admin_Service.dto.StatsResponse;
import CRBT.Admin_Service.Service.Dashboard_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class Dashboard_Controller {

    @Autowired
    private Dashboard_Service dashboardService;

    // Quick Stats (Users + Tunes count)
    @GetMapping("/stats")
    public ResponseEntity<StatsResponse> getQuickStats() {
        return ResponseEntity.ok(dashboardService.getQuickStats());
    }
}
