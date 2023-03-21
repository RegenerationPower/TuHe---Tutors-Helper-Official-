package Project.TuHe.services;

import Project.TuHe.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    void registration(UserEntity user);

    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;

}
