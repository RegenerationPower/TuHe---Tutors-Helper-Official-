package Project.TuHe.repositories;

import Project.TuHe.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u WHERE u.username = ?1")
    UserEntity findByUsername(String username);
    @Query("SELECT u FROM UserEntity u WHERE u.email = ?1")
    UserEntity findByEmail(String email);
    @Override
    @Query("SELECT u FROM UserEntity u WHERE u.id = ?1")
    Optional<UserEntity> findById(Long id);
    @Query("SELECT COUNT(u) > 0 FROM UserEntity u WHERE u.username = ?1")
    boolean existsByUsername(String username);

    @Query("SELECT COUNT(u) > 0 FROM UserEntity u WHERE u.email = ?1")
    boolean existsByEmail(String email);
}
