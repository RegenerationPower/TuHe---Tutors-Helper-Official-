package Project.TuHe.services.implementation;

import Project.TuHe.entities.EventEntity;
import Project.TuHe.repositories.EventRepository;
import Project.TuHe.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;
    public EventEntity saveEvent(EventEntity event) {
        if (event.getTitle() == null || event.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Event title must not be null or empty");
        }
        eventRepository.save(event);
        return event;
    }

    @Override
    public List<EventEntity> getAllEvents() {
        return eventRepository.findAll();
    }


}
