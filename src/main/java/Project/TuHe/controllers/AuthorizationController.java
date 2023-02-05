package Project.TuHe.controllers;

import Project.TuHe.entities.UserEntity;
import Project.TuHe.exceptions.UserAlreadyExistException;
import Project.TuHe.services.UserService;
import Project.TuHe.validations.UserValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthorizationController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserValidation userValidation;

    @RequestMapping(value = "/register")
    public String register(@Valid @ModelAttribute("user") UserEntity user, BindingResult bindingResult) throws UserAlreadyExistException {
//        if (bindingResult.hasErrors()) {
//            return "registerPage";
//        }
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
    public String login(@Valid @ModelAttribute("user") UserEntity user, BindingResult bindingResult) throws UserAlreadyExistException {
/*        if (bindingResult.hasErrors()) {
            return "loginPage";
        }*/
        try {
            userService.registration(user);
        }
        catch (Exception e) {
//            throw new UserAlreadyExistException("User with such username already exist");
            System.out.println("Bad");
        }
        return "loginPage";
    }

    @InitBinder
    private void bindValidator(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(userValidation);
    }

}