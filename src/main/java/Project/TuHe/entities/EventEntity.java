package Project.TuHe.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.BooleanFlag;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "event")
@Setter
@Getter
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable = false, length = 50, name = "title")
    @NotEmpty(message = "Title is required")
    private String title;

    @Column (nullable = false, length = 50, name = "startTime")
    @NotNull(message = "Start date is required")
    private Date startTime;

    @Column (nullable = false, length = 50, name = "endTime")
    @NotNull(message = "End date is required")
    private Date endTime;

    @NotNull
    @Column (name = "cost")
    private Double cost;

    //@NotNull
    @BooleanFlag
    @Column (name = "paid")
    private Boolean paid;

    @ManyToOne
    @JoinColumn(name = "userId")
    @NotNull
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private StudentEntity student;

    public EventEntity() {
    }

    public EventEntity(Long id, String title, Date startTime, Date endTime, Double cost, Boolean paid) {
        this.id = id;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cost = cost;
        this.paid = paid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }
}
