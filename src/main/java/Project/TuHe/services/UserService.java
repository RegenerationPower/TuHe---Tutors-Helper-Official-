package Project.TuHe.services;

import Project.TuHe.entities.UserEntity;
import Project.TuHe.exceptions.UserAlreadyExistException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public interface UserService {

    void registration(UserEntity user, BindingResult bindingResult) throws UserAlreadyExistException;
    UserEntity getUserById(Long userId);
    UserEntity getUserByUsername(String username);
}
