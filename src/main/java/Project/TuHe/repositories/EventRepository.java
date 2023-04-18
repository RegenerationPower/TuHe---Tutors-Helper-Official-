package Project.TuHe.repositories;

import Project.TuHe.entities.EventEntity;
import Project.TuHe.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, Long> {
    List<EventEntity> findByUser(UserEntity user);
}
