package Project.TuHe.services.implementation;

import Project.TuHe.entities.UserEntity;
import Project.TuHe.exceptions.UserAlreadyExistException;
import Project.TuHe.exceptions.UserNotFoundException;
import Project.TuHe.models.UserModel;
import Project.TuHe.repositories.UserRepository;
import Project.TuHe.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void registration(UserEntity user) {
        userRepository.save(user);
    }

}
