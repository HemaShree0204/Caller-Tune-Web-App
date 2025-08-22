package CRBT.Admin_Service.Client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TunesClient {

    private final RestTemplate restTemplate;

    public TunesClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public long getTotalTunes() {
        String url = "http://localhost:8082/api/tunes/count"; // change to actual service URL
        return restTemplate.getForObject(url, Long.class);
    }
}
