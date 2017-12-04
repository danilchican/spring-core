package ua.epam.spring.hometask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.epam.spring.hometask.dto.EventDTO;
import ua.epam.spring.hometask.facade.EventFacade;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventFacade eventFacade;

    @GetMapping
    public String viewAll(Model model) {
        model.addAttribute("events", eventFacade.findAll());
        return "events/view/index";
    }

    @GetMapping("/create")
    public String save(Model model) {
        model.addAttribute("event", new EventDTO());
        return "events/create";
    }

    @PostMapping("/create")
    public String save(@ModelAttribute("event") EventDTO event) {
        eventFacade.save(event);
        return "redirect:/events";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") long eventId) {
        eventFacade.delete(eventId);
        return "redirect:/events";
    }

    @GetMapping("/search/range/{to-date}")
    public ModelAndView showNextEventsToDate(@PathVariable("to-date") LocalDateTime toDate) {
        ModelAndView modelAndView = new ModelAndView("events/search/results");
        modelAndView.addObject("events", eventFacade.findNextEvents(toDate));

        return modelAndView;
    }

    @GetMapping("/search/range/{from-date}/{to-date}")
    public ModelAndView showEventsForDateRange(@PathVariable("from-date") LocalDateTime fromDate,
                                               @PathVariable("to-date") LocalDateTime toDate) {
        ModelAndView modelAndView = new ModelAndView("events/search/results");
        modelAndView.addObject("events", eventFacade.findForDateRange(fromDate, toDate));

        return modelAndView;
    }

    @GetMapping("/search/{name}")
    public ModelAndView searchEventByName(@PathVariable("name") String name) {
        ModelAndView modelAndView = new ModelAndView("events/search/results");
        eventFacade.findByName(name).ifPresent(event -> modelAndView.addObject("event", event));

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView viewEvent(@PathVariable("id") long eventId) {
        ModelAndView modelAndView = new ModelAndView("events/view");
        eventFacade.findById(eventId).ifPresent(event -> modelAndView.addObject("event", event));

        return modelAndView;
    }
}