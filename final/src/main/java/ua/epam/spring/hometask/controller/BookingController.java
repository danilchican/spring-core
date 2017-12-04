package ua.epam.spring.hometask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.epam.spring.hometask.facade.BookingFacade;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingFacade bookingFacade;

    @GetMapping("/purchased/{event-id}")
    public ModelAndView viewPurchasedTicketsForEvent(@PathVariable("event-id") int eventId,
                                                     @RequestParam("date") LocalDateTime date) {
        ModelAndView modelAndView = new ModelAndView("booking/tickets/view");
        modelAndView.addObject("tickets", bookingFacade.findPurchasedTicketsForEvent(eventId, date));

        return modelAndView;
    }
}
