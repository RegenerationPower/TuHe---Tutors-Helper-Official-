package Project.TuHe;

import Project.TuHe.entities.EventEntity;
import Project.TuHe.services.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class EventServiceTest {

    @Autowired
    private EventService eventService;

    @Test
    public void testSaveEvent() {
        EventEntity event = new EventEntity();
        event.setTitle("Test Event");
        event.setStartTime("2023-03-01 10:00:00");
        event.setEndTime("2023-03-01 12:00:00");

        EventEntity savedEvent = eventService.saveEvent(event);

        assertNotNull(savedEvent.getId());
        assertEquals(event.getTitle(), savedEvent.getTitle());
        assertEquals(event.getStartTime(), savedEvent.getStartTime());
        assertEquals(event.getEndTime(), savedEvent.getEndTime());
    }
}

