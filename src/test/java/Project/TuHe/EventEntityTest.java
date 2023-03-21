package Project.TuHe;

import Project.TuHe.entities.EventEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventEntityTest {

    @Test
    public void testGettersAndSetters() {
        EventEntity event = new EventEntity();
        event.setId(1L);
        event.setTitle("Test Event");
        event.setStartTime(LocalDateTime.parse("2023-02-20T08:00:00"));
        event.setEndTime(LocalDateTime.parse("2023-02-20T09:00:00"));

        assertEquals(1L, event.getId());
        assertEquals("Test Event", event.getTitle());
        assertEquals(LocalDateTime.parse("2023-02-20T08:00:00").toString(), event.getStartTime().toString());
        assertEquals(LocalDateTime.parse("2023-02-20T09:00:00").toString(), event.getEndTime().toString());
    }
}