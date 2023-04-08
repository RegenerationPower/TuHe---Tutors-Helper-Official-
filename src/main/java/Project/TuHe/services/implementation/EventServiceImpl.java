package Project.TuHe.services.implementation;

import Project.TuHe.DTOs.EventDTO;
import Project.TuHe.entities.EventEntity;
import Project.TuHe.repositories.EventRepository;
import Project.TuHe.services.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }

    public EventEntity saveEvent(EventDTO eventDTO) {
        EventEntity event = modelMapper.map(eventDTO, EventEntity.class);
        return eventRepository.save(event);
    }

    public List<EventDTO> getAllEvents() {
        List<EventEntity> events = eventRepository.findAll();
        return events.stream().map(event -> modelMapper.map(event, EventDTO.class)).collect(Collectors.toList());
    }

    public EventEntity updateEvent(Long id, EventDTO eventDTO) {
        EventEntity existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Event with id " + id + " not found"));
        existingEvent.setTitle(eventDTO.getTitle());
        existingEvent.setStartTime(eventDTO.getStartTime());
        existingEvent.setEndTime(eventDTO.getEndTime());
        existingEvent = eventRepository.save(existingEvent);
        return existingEvent;
    }

    public void deleteEvent(Long id) {
        Optional<EventEntity> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            eventRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Event with id " + id + " not found");
        }
    }

}