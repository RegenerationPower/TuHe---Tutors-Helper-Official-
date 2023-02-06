package Project.TuHe.repositories;

import Project.TuHe.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u WHERE u.username = ?1")
    UserEntity findByUsername(String username);
    @Query("SELECT u FROM UserEntity u WHERE u.email = ?1")
    UserEntity findByEmail(String email);
}
