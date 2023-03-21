package Project.TuHe.services;

import Project.TuHe.entities.EventEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EventService {
    List<EventEntity> getAllEvents();
    EventEntity saveEvent(EventEntity event);

}
