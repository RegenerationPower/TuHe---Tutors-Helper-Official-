package Project.TuHe.controllers;

import Project.TuHe.details.CustomUserDetails;
import Project.TuHe.entities.UserEntity;
import Project.TuHe.mappers.CustomUserDetailsMapper;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    private final CustomUserDetailsMapper customUserDetailsMapper;

    public UserRestController(CustomUserDetailsMapper customUserDetailsMapper) {
        this.customUserDetailsMapper = customUserDetailsMapper;
    }

    @GetMapping("")
    public UserEntity getUserDetails(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return customUserDetailsMapper.toUserEntity(userDetails);
    }

}
