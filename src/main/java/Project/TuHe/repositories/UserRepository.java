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
    @Query("SELECT u FROM UserEntity u WHERE u.userId = ?1")
    UserEntity findByUserId(Long userId);
    @Query("SELECT COUNT(u) > 0 FROM UserEntity u WHERE u.username = ?1")
    boolean existsByUsername(String username);

    @Query("SELECT COUNT(u) > 0 FROM UserEntity u WHERE u.email = ?1")
    boolean existsByEmail(String email);
}
