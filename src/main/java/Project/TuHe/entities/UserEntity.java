package Project.TuHe.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;


@Entity
@Table(name = "user")
@Setter
@Getter
@Component
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, unique = true, length = 50, name = "email")
    @NotEmpty(message = "Email is required")
    private String email;

    @Column (unique = true, nullable = false, length = 50, name = "username")
    @NotEmpty(message = "UserName is required")
    private String username;

    @Column (nullable = false, length = 100,  name = "password")
    @NotEmpty(message = "Password is required")
    private String password;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<EventEntity> events;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<StudentEntity> students;

    public Boolean userWithSuchEmailExist(String email) {
        return false;
    }

    public boolean userWithSuchUsernameExist(String username) {
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

    public List<EventEntity> getEvents() {
        return events;
    }

    public void setEvents(List<EventEntity> events) {
        this.events = events;
    }
}
