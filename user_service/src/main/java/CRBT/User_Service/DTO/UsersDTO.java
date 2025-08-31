package CRBT.User_Service.DTO;

public class UsersDTO {
    private Long users_id;
    private String username;
    private String role;
    private String plan_type;
    private String mobileNumber;
    private String callerTune;

    // Constructors
    public UsersDTO() {}

    public UsersDTO(Long users_id, String username, String role, String plan_type, String mobileNumber, String callerTune) {
        this.users_id = users_id;
        this.username = username;
        this.role = role;
        this.plan_type = plan_type;
        this.mobileNumber = mobileNumber;
        this.callerTune = callerTune;
    }

    // Getters and setters
    public Long getUsers_id() {
        return users_id;
    }

    public void setUsers_id(Long users_id) {
        this.users_id = users_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPlan_type() {
        return plan_type;
    }

    public void setPlan_type(String plan_type) {
        this.plan_type = plan_type;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getCallerTune() {
        return callerTune;
    }

    public void setCallerTune(String callerTune) {
        this.callerTune = callerTune;
    }
}
