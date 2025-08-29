package CRBT.Auth_Service.DTO;

public class AuthResponse {
    private String token;
    private Long users_id;

    // Constructors
    public AuthResponse() {}

    public AuthResponse(String token, Long users_id) {
        this.token = token;
        this.users_id = users_id;
    }

    // Getters and setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUsers_id() {
        return users_id;
    }

    public void setUsers_id(Long users_id) {
        this.users_id = users_id;
    }
}