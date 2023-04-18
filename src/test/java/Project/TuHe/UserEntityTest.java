package Project.TuHe;

import Project.TuHe.entities.EventEntity;
import Project.TuHe.entities.UserEntity;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class UserEntityTest {
	@InjectMocks
	private UserEntity user;

	@Test
	public void testGetId() {
		Long id = 1L;
		user.setId(id);
		assertEquals(id, user.getId());
	}

	@Test
	public void testGetUsername() {
		String username = "john.doe";
		user.setUsername(username);
		assertEquals(username, user.getUsername());
	}

	@Test
	public void testGetPassword() {
		String password = "password";
		user.setPassword(password);
		assertEquals(password, user.getPassword());
	}

	@Test
	public void testGetEmail() {
		String email = "john.doe@example.com";
		user.setEmail(email);
		assertEquals(email, user.getEmail());
	}

	@Test
	public void testGetEvents() {
		List<EventEntity> events = new ArrayList<>();
		events.add(new EventEntity());
		user.setEvents(events);
		assertEquals(events, user.getEvents());
	}

	@Test
	public void testUserWithSuchUsernameExist() {
		String username = "john.doe";
		assertFalse(user.userWithSuchUsernameExist(username));
	}

	@Test
	public void testSetId() {
		Long id = 1L;
		user.setId(id);
		assertEquals(id, user.getId());
	}

	@Test
	public void testSetUsername() {
		String username = "john.doe";
		user.setUsername(username);
		assertEquals(username, user.getUsername());
	}

	@Test
	public void testSetPassword() {
		String password = "password";
		user.setPassword(password);
		assertEquals(password, user.getPassword());
	}

	@Test
	public void testSetEmail() {
		String email = "john.doe@example.com";
		user.setEmail(email);
		assertEquals(email, user.getEmail());
	}

	@Test
	public void testSetEvents() {
		List<EventEntity> events = new ArrayList<>();
		events.add(new EventEntity());
		user.setEvents(events);
		assertEquals(events, user.getEvents());
	}
}
