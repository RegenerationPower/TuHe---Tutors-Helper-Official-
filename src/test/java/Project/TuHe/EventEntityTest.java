package Project.TuHe;

import Project.TuHe.entities.EventEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventEntityTest {

    @Test
    public void testGettersAndSetters() {
        EventEntity event = new EventEntity();
        event.setId(1L);
        event.setTitle("Test Event");
        event.setStartTime("2023-02-19 08:00:00");
        event.setEndTime("2023-02-19 09:00:00");

        assertEquals(1L, event.getId());
        assertEquals("Test Event", event.getTitle());
        assertEquals("2023-02-19 08:00:00", event.getStartTime());
        assertEquals("2023-02-19 09:00:00", event.getEndTime());
    }
}