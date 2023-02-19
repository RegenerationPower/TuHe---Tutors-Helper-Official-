package Project.TuHe.services.implementation;

import Project.TuHe.entities.EventEntity;
import Project.TuHe.repositories.EventRepository;
import Project.TuHe.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    @Override
    public EventEntity saveEvent(EventEntity event) {
        eventRepository.save(event);
        return event;
    }
}
