/*
package Project.TuHe;

import Project.TuHe.DTOs.EventDTO;
import Project.TuHe.controllers.EventController;
import Project.TuHe.entities.EventEntity;
import Project.TuHe.services.EventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventControllerTest {

    @Mock
    private EventService eventService;

    private final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void testCreateEvent() {
        // Set up the test input
        EventDTO eventDTO = new EventDTO();
        eventDTO.setTitle("Test Event");
        eventDTO.setStartTime(LocalDateTime.parse("2023-02-20T08:00:00"));
        eventDTO.setEndTime(LocalDateTime.parse("2023-02-20T09:00:00"));

        // Set up the expected output
        EventEntity expectedEvent = new EventEntity();
        expectedEvent.setTitle("Test Event");
        expectedEvent.setStartTime(LocalDateTime.parse("2023-02-20T08:00:00"));
        expectedEvent.setEndTime(LocalDateTime.parse("2023-02-20T09:00:00"));
        expectedEvent.setId(1L);

        when(eventService.saveEvent(any())).thenReturn(modelMapper.map(expectedEvent, EventEntity.class));

        // Call the method being tested
        EventController eventController = new EventController(eventService, modelMapper);
        ResponseEntity<EventEntity> result = eventController.createEvent(eventDTO);

        // Check the response status code and body
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(expectedEvent.getTitle(), result.getBody().getTitle());
        assertEquals(expectedEvent.getStartTime(), result.getBody().getStartTime());
        assertEquals(expectedEvent.getEndTime(), result.getBody().getEndTime());
        assertEquals(expectedEvent.getId(), result.getBody().getId());
    }

}*/
