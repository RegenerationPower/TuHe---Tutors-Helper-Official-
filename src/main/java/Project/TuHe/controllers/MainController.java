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
    @GetMapping("/")
    public String Main(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model){
        model.addAttribute("name", name);
        return "mainPage";
    }

    @RequestMapping("/cal")
    public String Cal(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model){
        model.addAttribute("name", name);
        return "calendarPage";
    }

    @RestController
    @RequestMapping("/api/events")
    public class EventController {

        @PostMapping
        public EventEntity createEvent(@RequestBody EventEntity event) {
            return eventService.saveEvent(event);
        }
    }
}
