package CRBT.Admin_Service_Service;



import CRBT.Admin_Service.Client.SubscriptionClient;
import CRBT.Admin_Service.Client.TunesClient;
import CRBT.Admin_Service.Service.Dashboard_Service;
import CRBT.Admin_Service.dto.StatsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class Dashboard_ServiceTest {

    @Mock
    private SubscriptionClient subscriptionClient;

    @Mock
    private TunesClient tunesClient;

    @InjectMocks
    private Dashboard_Service dashboardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetQuickStats() {
        when(subscriptionClient.getTotalUsers()).thenReturn(100L);
        when(tunesClient.getTotalTunes()).thenReturn(50L);
        when(subscriptionClient.getTotalSubscriptions()).thenReturn(20L);

        StatsResponse stats = dashboardService.getQuickStats();

        assertEquals(100L, stats.getTotalUsers());
        assertEquals(50L, stats.getTotalTunes());
        assertEquals(20L, stats.getTotalSubscriptions());
    }
}
