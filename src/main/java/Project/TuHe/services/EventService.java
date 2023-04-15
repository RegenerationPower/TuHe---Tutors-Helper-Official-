package Project.TuHe.services;

import Project.TuHe.DTOs.EventDTO;
import Project.TuHe.entities.EventEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {
    List<EventDTO> getAllEvents(Long userId);
    EventDTO saveEvent(Long userId, EventDTO eventDTO);
    EventDTO updateEvent(Long userId, Long id, EventDTO eventDTO);
    void deleteEvent(Long id);
}
