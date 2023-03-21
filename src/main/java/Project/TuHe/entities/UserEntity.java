package Project.TuHe.entities;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import static javax.crypto.Cipher.SECRET_KEY;

@Entity
@Table(name = "user")
@Setter
@Getter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 50)
    @NotEmpty(message = "Email is required")
    private String email;
    @Column (unique = true, nullable = false, length = 50)
    @NotEmpty(message = "UserName is required")
    private String username;
    @Column (nullable = false, length = 100)
    @NotEmpty(message = "Password is required")
    private String password;

    public UserEntity() {
    }

    public Boolean userWithSuchUsernameExist(String username) {
        return false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
