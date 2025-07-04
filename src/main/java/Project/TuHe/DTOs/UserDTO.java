package Project.TuHe.DTOs;

import jakarta.validation.constraints.NotEmpty;

public class UserDTO {

    private Long id;
    @NotEmpty(message = "Email is required")
    private String email;

    @NotEmpty(message = "UserName is required")
    private String username;

    @NotEmpty(message = "Password is required")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
