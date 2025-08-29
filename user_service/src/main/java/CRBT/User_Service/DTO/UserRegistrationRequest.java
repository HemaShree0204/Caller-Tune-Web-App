package CRBT.User_Service.DTO;

public class UserRegistrationRequest {
    private String username;
    private String email;
    private String password; // This will be ignored in user service, used by auth service
    private String plan_type;
    private String role;
    private String mobileNumber;
    
    // Constructors
    public UserRegistrationRequest() {}
    
    // Getters and setters
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPlan_type() {
        return plan_type;
    }
    
    public void setPlan_type(String plan_type) {
        this.plan_type = plan_type;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    public String getMobileNumber() { 
    	return mobileNumber; 
    	}
    public void setMobileNumber(String mobileNumber) { 
    	this.mobileNumber = mobileNumber; 
    	}
}