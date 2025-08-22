package CRBT.Admin_Service.Service;

import CRBT.Admin_Service.dto.StatsResponse;
import CRBT.Admin_Service.Client.SubscriptionClient;
import CRBT.Admin_Service.Client.TunesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Dashboard_Service {

    @Autowired
    private SubscriptionClient subscriptionClient;

    @Autowired
    private TunesClient tunesClient;

    // Get Quick Stats
    public StatsResponse getQuickStats() {
        long totalUsers = subscriptionClient.getTotalUsers();   // from Subscription service
        long totalTunes = tunesClient.getTotalTunes();          // from Tunes service
        long totalSubscriptions = subscriptionClient.getTotalSubscriptions();
        
        return new StatsResponse(totalUsers, totalTunes ,totalSubscriptions );
    }
}
