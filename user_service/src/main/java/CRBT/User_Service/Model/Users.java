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
    
    @Column(nullable = true)
    private String defaultTuneFileName;
    
    @Column(unique = true, nullable = false)
    private String mobileNumber;
    
    @Column(name = "caller_tune")
    private String callerTune;
    
    // Constructors
    public Users() {}
    
    public Users(String username, String email, Role role, String planType,String mobileNumber,String callerTune) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.planType = planType;
        this.mobileNumber=mobileNumber;
        this.callerTune=callerTune;
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
    
    public String getDefaultTuneFileName() {
    	return defaultTuneFileName; 
    	}
    
    public void setDefaultTuneFileName(String defaultTuneFileName) {
    	this.defaultTuneFileName = defaultTuneFileName; 
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