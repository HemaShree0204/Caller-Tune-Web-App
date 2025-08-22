package CRBT.Admin_Service.Client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SubscriptionClient {

    private final RestTemplate restTemplate;

    public SubscriptionClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public long getTotalUsers() {
        String url = "http://localhost:8083/api/users/count"; // change to actual service URL
        return restTemplate.getForObject(url, Long.class);
    }

    public long getTotalSubscriptions() {
        String url = "http://localhost:8083/api/subscriptions/count"; // change to actual service URL
        return restTemplate.getForObject(url, Long.class);
    }
}
