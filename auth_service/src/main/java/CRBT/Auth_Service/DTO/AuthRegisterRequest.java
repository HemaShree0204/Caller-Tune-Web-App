package CRBT.Auth_Service.DTO;

public class AuthRegisterRequest {
    private String username;
    private String password;
    private String mobileNumber;  // ✅ Consistent naming
    private Long users_id;

    public AuthRegisterRequest() {}

    public AuthRegisterRequest(String username, String password, String mobileNumber, Long users_id) {
        this.username = username;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.users_id = users_id;
    }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getMobileNumber() { return mobileNumber; }   // ✅ FIXED
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; } // ✅ FIXED

    public Long getUsers_id() { return users_id; }
    public void setUsers_id(Long users_id) { this.users_id = users_id; }
}
