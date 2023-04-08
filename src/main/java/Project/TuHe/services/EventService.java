package Project.TuHe.services;

import Project.TuHe.DTOs.EventDTO;
import Project.TuHe.entities.EventEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {
    List<EventDTO> getAllEvents();
    EventEntity saveEvent(EventDTO eventDTO);
    EventEntity updateEvent(Long id, EventDTO eventDTO);
    void deleteEvent(Long id);
}
