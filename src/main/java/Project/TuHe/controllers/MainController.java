package Project.TuHe.controllers;

import Project.TuHe.entities.EventEntity;
import Project.TuHe.repositories.EventRepository;
import Project.TuHe.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventService eventService;

    @RequestMapping("/")
    public String Cal(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model){
        model.addAttribute("name", name);
        return "calendarPage";
    }

}
