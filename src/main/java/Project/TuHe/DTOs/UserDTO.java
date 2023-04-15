package Project.TuHe.DTOs;

import jakarta.validation.constraints.NotEmpty;

public class UserDTO {

    private Long userId;
    @NotEmpty(message = "Email is required")
    private String email;

    @NotEmpty(message = "UserName is required")
    private String username;

    @NotEmpty(message = "Password is required")
    private String password;

    public UserDTO(Long userId, String email, String username, String password) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
