package CRBT.User_Service.DTO;

public class UsersDTO {
    private Long users_id;
    private String username;
    private String email;
    private String role;
    private String plan_type;
    
    // Constructors
    public UsersDTO() {}
    
    public UsersDTO(Long users_id, String username, String email, String role, String plan_type) {
        this.users_id = users_id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.plan_type = plan_type;
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
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
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
}