package Project.TuHe.controllers;

import Project.TuHe.DTOs.EventDTO;
import Project.TuHe.entities.EventEntity;
import Project.TuHe.services.EventService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;
    private final ModelMapper modelMapper;

    @Autowired
    public EventController(EventService eventService, ModelMapper modelMapper) {
        this.eventService = eventService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<EventEntity>> getAllEvents(@PathVariable("userId") Long userId) {
        List<EventDTO> eventDTOs = eventService.getAllEvents(userId);
        List<EventEntity> eventEntities = new ArrayList<>();
        for (EventDTO eventDTO : eventDTOs) {
            if (eventDTO != null) {
                EventEntity eventEntity = modelMapper.map(eventDTO, EventEntity.class);
                eventEntities.add(eventEntity);
            }
        }
        return ResponseEntity.ok(eventEntities);
    }

    @PostMapping("/users/{userId}")
    public ResponseEntity<EventEntity> createEvent(@PathVariable("userId") Long userId, @RequestBody @Valid EventDTO eventDTO) {
        EventDTO createdEventDTO = eventService.saveEvent(userId, eventDTO);
        EventEntity createdEventEntity = modelMapper.map(createdEventDTO, EventEntity.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEventEntity);
    }

    @PatchMapping("/users/{userId}/{eventId}")
    public ResponseEntity<EventEntity> updateEvent(@PathVariable("userId") Long userId, @PathVariable("eventId") Long eventId, @RequestBody @Valid EventDTO eventDTO) {
        EventDTO updatedEventDTO = eventService.updateEvent(userId, eventId, eventDTO);
        EventEntity updatedEventEntity = modelMapper.map(updatedEventDTO, EventEntity.class);
        return ResponseEntity.ok(updatedEventEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable("id") Long id) {
        try {
            eventService.deleteEvent(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

}