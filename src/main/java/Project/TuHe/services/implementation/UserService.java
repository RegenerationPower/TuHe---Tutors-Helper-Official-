package Project.TuHe.services.implementation;

import Project.TuHe.entities.UserEntity;
import Project.TuHe.exceptions.UserAlreadyExistException;
import Project.TuHe.exceptions.UserNotFoundException;
import Project.TuHe.models.UserModel;
import Project.TuHe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        if(userRepository.findByUsername(user.getUsername()) != null){
            throw new UserAlreadyExistException("User with such username already exist");
        }
        return userRepository.save(user);
    }

    public UserModel getSpecificUser(Long id) throws UserNotFoundException {
        UserEntity user = userRepository.findById(id).get();
        if(user == null){
            throw new UserNotFoundException("User not found");
        }
        return UserModel.toModel(user);
    }

    public Long deleteUser(Long id) {
        userRepository.deleteById(id);
        return id;
    }
}
