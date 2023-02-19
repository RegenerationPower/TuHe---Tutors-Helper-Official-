package Project.TuHe.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

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

    @Column (nullable = false, length = 50)
    @NotEmpty(message = "Start date is required")
    private String startTime;

    @Column (nullable = false, length = 50)
    @NotEmpty(message = "End date is required")
    private String endTime;

    public EventEntity() {
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
