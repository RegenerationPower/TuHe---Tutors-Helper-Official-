package Project.TuHe.controllers;

import Project.TuHe.entities.UserEntity;
import Project.TuHe.services.implementation.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String home(Model model, UserEntity user) {
        model.addAttribute("user", user);
        return "registerPage";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("user") UserEntity user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.out.println("BINDING RESULT ERROR");
            return "registerPage";
        }
        try {
            userService.registration(user);
        }
        catch (Exception e) {
            return "registerPage";
        }
        return "main";
    }
}