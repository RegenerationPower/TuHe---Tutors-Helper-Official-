package Project.TuHe.details;

import Project.TuHe.DTOs.UserDTO;
import Project.TuHe.entities.UserEntity;
import Project.TuHe.mappers.CustomUserDetailsMapper;
import Project.TuHe.repositories.UserRepository;
import Project.TuHe.validations.UserValidation;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    private final UserRepository userRepository;
    private final CustomUserDetailsMapper customUserDetailsMapper;
    private final UserValidation userValidation;
    @Autowired
    public CustomUserDetailsServiceImpl(UserRepository userRepository,
                                        CustomUserDetailsMapper customUserDetailsMapper, UserValidation userValidation) {
        this.userRepository = userRepository;
        this.customUserDetailsMapper = customUserDetailsMapper;
        this.userValidation = userValidation;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }

}
