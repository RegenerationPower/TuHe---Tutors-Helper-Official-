package Project.TuHe.controllers;

import Project.TuHe.details.CustomUserDetails;
import Project.TuHe.entities.UserEntity;
import Project.TuHe.mappers.CustomUserDetailsMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    private final CustomUserDetailsMapper customUserDetailsMapper;

    public UserController(CustomUserDetailsMapper customUserDetailsMapper) {
        this.customUserDetailsMapper = customUserDetailsMapper;
    }

    @GetMapping("/")
    public String getUserName(Authentication authentication, Model model) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UserEntity userEntity = customUserDetailsMapper.toUserEntity(userDetails);
        model.addAttribute("userName", userEntity.getUsername());
        return "calendarPage";
    }


}
