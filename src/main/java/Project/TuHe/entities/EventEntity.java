package Project.TuHe.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @Column (nullable = false, length = 50)
    @NotEmpty(message = "Title is required")
    private String title;

    @Column (nullable = false, length = 50, name = "startTime")
    @NotNull(message = "Start date is required")
    private Date startTime;

    @Column (nullable = false, length = 50, name = "endTime")
    @NotNull(message = "End date is required")
    private Date endTime;

    public EventEntity() {
    }

    public EventEntity(Long id, String title, Date startTime, Date endTime) {
        this.id = id;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
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

}
