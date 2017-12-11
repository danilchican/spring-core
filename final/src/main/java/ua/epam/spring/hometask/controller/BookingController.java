package ua.epam.spring.hometask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.epam.spring.hometask.dto.TicketDTO;
import ua.epam.spring.hometask.facade.BookingFacade;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Set;

import static ua.epam.spring.hometask.validator.CommonValidator.DATETIME_REGEX;

@Controller
@RequestMapping("/booking")
@Validated
public class BookingController {

    @Autowired
    private BookingFacade bookingFacade;

    @GetMapping("/purchased/{event-id}")
    @RequestMapping(value = "/purchased/{event-id}", method = RequestMethod.GET)
    public ModelAndView viewPurchasedTicketsForEvent(@Pattern(regexp = "\\d", message = "Event id should be a number")
                                                     @PathVariable("event-id") int eventId,
                                                     @Pattern(regexp = DATETIME_REGEX, message = "Date should be like 0000-00-00 00:00:00")
                                                     @RequestParam("date") LocalDateTime date) {
        ModelAndView modelAndView = new ModelAndView("booking/tickets/view");
        modelAndView.addObject("tickets", bookingFacade.findPurchasedTicketsForEvent(eventId, date));

        return modelAndView;
    }

    @RequestMapping(value = "/tickets/book", method = RequestMethod.POST)
    public String bookTickets(@RequestParam("tickets") Set<TicketDTO> tickets) {
        bookingFacade.bookTickets(tickets);
        return "redirect:/booking";
    }
}
