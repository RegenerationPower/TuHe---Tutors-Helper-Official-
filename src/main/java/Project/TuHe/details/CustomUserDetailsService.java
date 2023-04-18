package Project.TuHe.details;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;



@Service
public interface CustomUserDetailsService extends UserDetailsService {

    CustomUserDetails loadUserByUsername(String username);
}
