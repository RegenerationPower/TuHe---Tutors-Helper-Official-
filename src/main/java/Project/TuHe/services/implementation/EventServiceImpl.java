package Project.TuHe.services.implementation;

import Project.TuHe.DTOs.EventDTO;
import Project.TuHe.entities.EventEntity;
import Project.TuHe.entities.StudentEntity;
import Project.TuHe.entities.UserEntity;
import Project.TuHe.repositories.EventRepository;
import Project.TuHe.repositories.StudentRepository;
import Project.TuHe.repositories.UserRepository;
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
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, ModelMapper modelMapper, UserRepository userRepository, StudentRepository studentRepository) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
    }

    public List<EventDTO> getAllEvents(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User with id " + userId + " not found"));
        List<EventEntity> events = eventRepository.findByUser(user);
        return events.stream().map(event -> modelMapper.map(event, EventDTO.class)).collect(Collectors.toList());
    }

    public EventDTO saveEvent(Long userId, EventDTO eventDTO) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User with id " + userId + " not found"));

        EventEntity event = modelMapper.map(eventDTO, EventEntity.class);
        event.setUser(user);

        return getEventDTO(eventDTO, event);
    }

    public EventDTO updateEvent(Long userId, Long id, EventDTO eventDTO) {
        EventEntity existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Event with id " + id + " not found"));

        existingEvent.setTitle(eventDTO.getTitle());
        existingEvent.setStartTime(eventDTO.getStartTime());
        existingEvent.setEndTime(eventDTO.getEndTime());
        existingEvent.setCost(eventDTO.getCost());
        existingEvent.setPaid(eventDTO.getPaid());

        return getEventDTO(eventDTO, existingEvent);
    }

    public void deleteEvent(Long id) {
        Optional<EventEntity> eventOptional = eventRepository.findById(id);
        if (eventOptional.isPresent()) {
            eventRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Event with id " + id + " not found");
        }
    }

    private EventDTO getEventDTO(EventDTO eventDTO, EventEntity existingEvent) {
        if (eventDTO.getStudentId() != null) {
            StudentEntity student = studentRepository.findById(eventDTO.getStudentId())
                    .orElseThrow(() -> new NoSuchElementException("Student with id " + eventDTO.getStudentId() + " not found"));
            existingEvent.setStudent(student);
        }
        else {
            existingEvent.setStudent(null);
        }

        existingEvent = eventRepository.save(existingEvent);
        return modelMapper.map(existingEvent, EventDTO.class);
    }

}