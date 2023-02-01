package Project.TuHe.controllers;

import Project.TuHe.entities.UserEntity;
import Project.TuHe.exceptions.UserAlreadyExistException;
import Project.TuHe.services.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StartController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String home(Model model, UserEntity user) {
        model.addAttribute("user", user);
        return "registerPage";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") UserEntity user){
        try {
            userService.registration(user);
        }
        catch (Exception e) {
            return "Error happened";
        }
        return "main";
    }
}