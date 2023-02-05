package Project.TuHe.services;

import Project.TuHe.entities.UserEntity;
import Project.TuHe.exceptions.UserAlreadyExistException;
import Project.TuHe.exceptions.UserNotFoundException;
import Project.TuHe.models.UserModel;
import Project.TuHe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void registration(UserEntity user);

}
