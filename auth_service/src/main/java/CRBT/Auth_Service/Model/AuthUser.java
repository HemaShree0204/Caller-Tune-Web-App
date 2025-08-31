package CRBT.Auth_Service.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "auth_users")
public class AuthUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "users_id", nullable = false)
    private Long users_id; // Reference to user-service user ID
    
    @Column(unique = true, nullable = false)
    private String mobile;

    public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public AuthUser() {}

    public AuthUser(String username, String password, Long users_id) {
        this.username = username;
        this.password = password;
        this.users_id = users_id;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Long getUsers_id() { return users_id; }
    public void setUsers_id(Long users_id) { this.users_id = users_id; }
}
