package CRBT.Admin_Service.dto;
public class StatsResponse {
    private long totalUsers;
    private long totalTunes;
    private long totalSubscriptions;
    
    
    public StatsResponse() {}
    public StatsResponse(long totalUsers, long totalTunes, long totalSubscriptions) {
        this.totalUsers = totalUsers;
        this.totalTunes = totalTunes;
        this.totalSubscriptions = totalSubscriptions;
    }
    public long getTotalUsers() {
        return totalUsers;
    }
    public void setTotalUsers(long totalUsers) {
        this.totalUsers = totalUsers;
    }
    public long getTotalTunes() {
        return totalTunes;
    }
    public void setTotalTunes(long totalTunes) {
        this.totalTunes = totalTunes;
    }
    public long getTotalSubscriptions() {
        return totalSubscriptions;
    }
    public void setTotalSubscriptions(long totalSubscriptions) {
        this.totalSubscriptions = totalSubscriptions;
    }
}
