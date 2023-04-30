package Project.TuHe.details;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserDetailsService extends UserDetailsService {
    CustomUserDetails loadUserByUsername(String username);
}
