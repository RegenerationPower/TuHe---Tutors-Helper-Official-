package Project.TuHe.services.implementation;

import Project.TuHe.entities.UserEntity;
import Project.TuHe.exceptions.UserAlreadyExistException;
import Project.TuHe.repositories.UserRepository;
import Project.TuHe.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public void registration(UserEntity user, BindingResult bindingResult) throws UserAlreadyExistException {
        if (userRepository.existsByUsername(user.getUsername())) {
            bindingResult.rejectValue("username", "error.username", "This username already exists");
            throw new UserAlreadyExistException("This username already exists");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            bindingResult.rejectValue("email", "error.email", "This email already exists");
            throw new UserAlreadyExistException("This email already exists");
        }
        userRepository.save(user);
    }


    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
