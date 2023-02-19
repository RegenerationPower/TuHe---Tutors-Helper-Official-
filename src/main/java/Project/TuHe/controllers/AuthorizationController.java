package Project.TuHe.controllers;

import Project.TuHe.entities.UserEntity;
import Project.TuHe.exceptions.UserAlreadyExistException;
import Project.TuHe.services.UserService;
import Project.TuHe.validations.UserValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthorizationController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserValidation userValidation;

    @RequestMapping(value = "/signUp")
    public String register(UserEntity user, Model model, BindingResult bindingResult) throws UserAlreadyExistException {
        if (bindingResult.hasErrors()) {
            return "registerPage";
       }
        model.addAttribute("user", new UserEntity());
        // set to omit error
        user.setPassword("12345");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        try {
            userService.registration(user);
        }
        catch (Exception e) {
//            throw new UserAlreadyExistException("User with such username already exist");
            System.out.println("So Bad");
        }
        return "registerPage";
    }


    @RequestMapping(value = "/login")
    public String login(@ModelAttribute("user") UserEntity user, BindingResult bindingResult) {
        return "loginPage";
    }

    @InitBinder
    private void bindValidator(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(userValidation);
    }

}