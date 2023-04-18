package Project.TuHe;

import Project.TuHe.entities.EventEntity;
import Project.TuHe.entities.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventEntityTest {
    private EventEntity eventEntity;

    @BeforeEach
    public void setUp() {
        eventEntity = new EventEntity();
    }

    @Test
    public void testSetAndGetId() {
        Long id = 1L;
        eventEntity.setId(id);
        assertEquals(id, eventEntity.getId());
    }

    @Test
    public void testSetAndGetTitle() {
        String title = "Test Event";
        eventEntity.setTitle(title);
        assertEquals(title, eventEntity.getTitle());
    }

    @Test
    public void testSetAndGetStartTime() {
        Date startTime = new Date();
        eventEntity.setStartTime(startTime);
        assertEquals(startTime, eventEntity.getStartTime());
    }

    @Test
    public void testSetAndGetEndTime() {
        Date endTime = new Date();
        eventEntity.setEndTime(endTime);
        assertEquals(endTime, eventEntity.getEndTime());
    }

    @Test
    public void testSetAndGetUser() {
        UserEntity user = new UserEntity();
        eventEntity.setUser(user);
        assertEquals(user, eventEntity.getUser());
    }

    @Test
    public void testSetAndGetCost() {
        Double cost = 100.0;
        eventEntity.setCost(cost);
        assertEquals(cost, eventEntity.getCost());
    }

    @Test
    public void testSetAndGetPaid() {
        Boolean paid = true;
        eventEntity.setPaid(paid);
        assertEquals(paid, eventEntity.getPaid());
    }
}
