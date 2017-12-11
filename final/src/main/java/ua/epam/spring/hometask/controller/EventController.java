package ua.epam.spring.hometask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.epam.spring.hometask.dto.EventDTO;
import ua.epam.spring.hometask.facade.EventFacade;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import static ua.epam.spring.hometask.validator.CommonValidator.DATETIME_REGEX;

@Controller
@RequestMapping("/events")
@Validated
public class EventController {

    @Autowired
    private EventFacade eventFacade;

    @RequestMapping(method = RequestMethod.GET)
    public String viewAll(Model model) {
        model.addAttribute("events", eventFacade.findAll());
        return "events/view/index";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String save(Model model) {
        model.addAttribute("event", new EventDTO());
        return "events/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(@ModelAttribute("event") EventDTO event) {
        eventFacade.save(event);
        return "redirect:/events";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(@Pattern(regexp = "\\d", message = "Event id should be a number")
                         @RequestParam("id") long eventId) {
        eventFacade.delete(eventId);
        return "redirect:/events";
    }

    @RequestMapping(value = "/search/range/{to-date}", method = RequestMethod.GET)
    public ModelAndView showNextEventsToDate(
            @Pattern(regexp = DATETIME_REGEX, message = "ToDate should be like 0000-00-00 00:00:00")
            @PathVariable("to-date") LocalDateTime toDate) {
        ModelAndView modelAndView = new ModelAndView("events/search/results");
        modelAndView.addObject("events", eventFacade.findNextEvents(toDate));

        return modelAndView;
    }

    @RequestMapping(value = "/search/range/{from-date}/{to-date}", method = RequestMethod.GET)
    public ModelAndView showEventsForDateRange(
            @Pattern(regexp = DATETIME_REGEX, message = "FromDate should be like 0000-00-00 00:00:00")
            @PathVariable("from-date") LocalDateTime fromDate,
            @Pattern(regexp = DATETIME_REGEX, message = "ToDate should be like 0000-00-00 00:00:00")
            @PathVariable("to-date") LocalDateTime toDate) {
        ModelAndView modelAndView = new ModelAndView("events/search/results");
        modelAndView.addObject("events", eventFacade.findForDateRange(fromDate, toDate));

        return modelAndView;
    }

    @RequestMapping(value = "/search/{name}", method = RequestMethod.GET)
    public ModelAndView searchEventByName(@Size(min = 1, max = 30) @PathVariable("name") String name) {
        ModelAndView modelAndView = new ModelAndView("events/search/results");
        eventFacade.findByName(name).ifPresent(event -> modelAndView.addObject("event", event));

        return modelAndView;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView viewEvent(@Pattern(regexp = "\\d", message = "Event id should be a number")
                                  @PathVariable("id") long eventId) {
        ModelAndView modelAndView = new ModelAndView("events/view");
        eventFacade.findById(eventId).ifPresent(event -> modelAndView.addObject("event", event));

        return modelAndView;
    }
}