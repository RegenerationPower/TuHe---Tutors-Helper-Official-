package Project.TuHe;

import Project.TuHe.entities.UserEntity;
import Project.TuHe.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
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
		user.setUsername("Ravi35");
		user.setPassword("ravi2020");
		UserEntity savedUser = userRepository.save(user);
		UserEntity existUser = entityManager.find(UserEntity.class, savedUser.getId());
		assertThat(user.getUsername()).isEqualTo(existUser.getUsername());

	}

}
