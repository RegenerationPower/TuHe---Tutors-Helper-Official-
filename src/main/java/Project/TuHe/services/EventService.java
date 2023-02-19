package Project.TuHe.services;

import Project.TuHe.entities.EventEntity;
import org.springframework.stereotype.Service;

@Service
public interface EventService {

    EventEntity saveEvent(EventEntity event);
}
