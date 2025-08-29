package CRBT.User_Service.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long users_id;
    
    @Column(unique = true, nullable = false)
    private String username;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    
    private String planType;
    
    // Constructors
    public Users() {}
    
    public Users(String username, String email, Role role, String planType) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.planType = planType;
    }
    
    // Enum
    public enum Role {
        USER, ADMIN
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
    
    public Role getRole() {
        return role;
    }
    
    public void setRole(Role role) {
        this.role = role;
    }
    
    public String getPlanType() {
        return planType;
    }
    
    public void setPlanType(String planType) {
        this.planType = planType;
    }
}