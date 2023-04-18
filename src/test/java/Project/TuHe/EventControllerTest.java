package Project.TuHe;

import Project.TuHe.DTOs.EventDTO;
import Project.TuHe.controllers.EventController;
import Project.TuHe.entities.EventEntity;
import Project.TuHe.services.EventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class EventControllerTest {

    @InjectMocks
    private EventController eventController;

    @Mock
    private EventService eventService;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void testGetAllEvents() {
        // Arrange
        Long userId = 1L;
        List<EventDTO> eventDTOs = new ArrayList<>();
        eventDTOs.add(new EventDTO());
        when(eventService.getAllEvents(userId)).thenReturn(eventDTOs);
        when(modelMapper.map(any(), any())).thenReturn(new EventEntity());

        // Act
        ResponseEntity<List<EventEntity>> responseEntity = eventController.getAllEvents(userId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(1, responseEntity.getBody().size());
        verify(eventService, times(1)).getAllEvents(userId);
        verify(modelMapper, times(1)).map(any(), any());
    }

    @Test
    public void testCreateEvent() {
        // Arrange
        Long userId = 1L;
        EventDTO eventDTO = new EventDTO();
        EventDTO createdEventDTO = new EventDTO();
        when(eventService.saveEvent(userId, eventDTO)).thenReturn(createdEventDTO);
        when(modelMapper.map(any(), any())).thenReturn(new EventEntity());

        // Act
        ResponseEntity<EventEntity> responseEntity = eventController.createEvent(userId, eventDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        verify(eventService, times(1)).saveEvent(userId, eventDTO);
        verify(modelMapper, times(1)).map(any(), any());
    }

    @Test
    public void testUpdateEvent() {
        // Arrange
        Long userId = 1L;
        Long eventId = 1L;
        EventDTO eventDTO = new EventDTO();
        EventDTO updatedEventDTO = new EventDTO();
        when(eventService.updateEvent(userId, eventId, eventDTO)).thenReturn(updatedEventDTO);
        when(modelMapper.map(any(), any())).thenReturn(new EventEntity());

        // Act
        ResponseEntity<EventEntity> responseEntity = eventController.updateEvent(userId, eventId, eventDTO);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        verify(eventService, times(1)).updateEvent(userId, eventId, eventDTO);
        verify(modelMapper, times(1)).map(any(), any());
    }

    @Test
    public void testDeleteEvent() {
        // Arrange
        Long eventId = 1L;
        doNothing().when(eventService).deleteEvent(eventId);

        // Act
        ResponseEntity<?> responseEntity = eventController.deleteEvent(eventId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(eventService, times(1)).deleteEvent(eventId);
    }

    @Test
    public void testDeleteEvent_NotFound() {
        // Arrange
        Long eventId = 1L;
        doThrow(NoSuchElementException.class).when(eventService).deleteEvent(eventId);

        // Act
        ResponseEntity<?> responseEntity = eventController.deleteEvent(eventId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(eventService, times(1)).deleteEvent(eventId);
    }
}

