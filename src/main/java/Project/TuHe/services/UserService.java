package Project.TuHe.services;

import Project.TuHe.entities.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void registration(UserEntity user);
    UserEntity getUserById(Long userId);
    UserEntity getUserByUsername(String username);
}
