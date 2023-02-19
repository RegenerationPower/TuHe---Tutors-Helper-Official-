package Project.TuHe;

import Project.TuHe.entities.UserEntity;
import Project.TuHe.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class TestUser {
	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepository;
	@Test
	void testRegisterUser() {
		UserEntity user = new UserEntity();
		user.setUsername("TestNam111111");
		user.setPassword("12345");
		user.setEmail("test111111@gmail.com");

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		UserEntity savedUser = userRepository.save(user);
		UserEntity existUser = entityManager.find(UserEntity.class, savedUser.getId());
		assertThat(user.getUsername()).isEqualTo(existUser.getUsername());

	}

	@Test
	void testEncryption() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String password = "12345678";
		String encodedPassword = passwordEncoder.encode(password);

		System.out.println();
		System.out.println("Password is         : " + password);
		System.out.println("Encoded Password is : " + encodedPassword);
		System.out.println();

		boolean isPasswordMatch = passwordEncoder.matches(password, encodedPassword);
		System.out.println("Password : " + password + "   isPasswordMatch    : " + isPasswordMatch);
	}

	@Test
	void testFindUserByEmail() {
		String email = "test2@gmail.com";

		UserEntity user = userRepository.findByEmail(email);
		assertThat(user).isNotNull();
	}

	@Test
	public void testUserCreation() {
		UserEntity user = new UserEntity();
		Assertions.assertNotNull(user);
	}

	@Test
	public void testSetAndGetId() {
		UserEntity user = new UserEntity();
		Long id = 1L;
		user.setId(id);
		Assertions.assertEquals(id, user.getId());
	}

	@Test
	public void testSetAndGetUsername() {
		UserEntity user = new UserEntity();
		String username = "testuser";
		user.setUsername(username);
		Assertions.assertEquals(username, user.getUsername());
	}

	@Test
	public void testSetAndGetEmail() {
		UserEntity user = new UserEntity();
		String email = "testuser@example.com";
		user.setEmail(email);
		Assertions.assertEquals(email, user.getEmail());
	}

	@Test
	public void testSetAndGetPassword() {
		UserEntity user = new UserEntity();
		String password = "testpassword";
		user.setPassword(password);
		Assertions.assertEquals(password, user.getPassword());
	}

	@Test
	public void testUserWithSuchUsernameExist() {
		UserEntity user = new UserEntity();
		String username = "testuser";
		Assertions.assertFalse(user.userWithSuchUsernameExist(username));
	}
}
