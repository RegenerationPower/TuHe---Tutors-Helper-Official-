package Project.TuHe.DTOs;

import Project.TuHe.entities.EventEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;


public class EventDTO {
    private Long id;
    @NotEmpty
    private String title;

    @NotNull
    private Date startTime;

    @NotNull
    private Date endTime;

    @NotNull
    private Double cost;

    //@NotNull
    private Boolean paid;

    public EventDTO(String title, Date startTime, Date endTime, Double cost, Boolean paid) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cost = cost;
        this.paid = paid;
    }

    public EventDTO() {

    }

    public static EventDTO fromEntity(EventEntity entity) {
        return new EventDTO(entity.getTitle(), entity.getStartTime(), entity.getEndTime(), entity.getCost(), entity.getPaid());
    }

    public EventEntity toEntity() {
        EventEntity entity = new EventEntity();
        entity.setTitle(title);
        entity.setStartTime(startTime);
        entity.setEndTime(endTime);
        entity.setCost(cost);
        entity.setPaid(paid);
        return entity;
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

    public double getCost() {
        return cost;
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
