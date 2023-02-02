package Project.TuHe.controllers;

import Project.TuHe.entities.UserEntity;
import Project.TuHe.exceptions.UserAlreadyExistException;
import Project.TuHe.services.implementation.UserService;
import Project.TuHe.validations.UserValidation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthorizationController {
    @Autowired
    private UserService userService;
    private UserValidation userValidation;
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String home(Model model, UserEntity user) {
        model.addAttribute("user", user);
        return "registerPage";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("user") UserEntity user, BindingResult bindingResult) throws UserAlreadyExistException {
        if (bindingResult.hasErrors()) {
            return "registerPage";
        }
        try {
            userService.registration(user);
        }
        catch (Exception e) {
            throw new UserAlreadyExistException("User with such username already exist");
        }
        return "loginPage";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid @ModelAttribute("user") UserEntity user, BindingResult bindingResult) throws UserAlreadyExistException {
        if (bindingResult.hasErrors()) {
            return "loginPage";
        }
        try {
            userService.registration(user);
        }
        catch (Exception e) {
            throw new UserAlreadyExistException("User with such username already exist");
        }
        return "main";
    }

    @InitBinder
    private void bindValidator(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(userValidation);
    }

}