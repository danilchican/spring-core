package ua.epam.spring.hometask.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.epam.spring.hometask.dto.TicketDTO;
import ua.epam.spring.hometask.facade.BookingFacade;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@RestController
@RequestMapping("/booking")
public class BookingRestController {

    @Autowired
    private BookingFacade bookingFacade;

    @GetMapping("/purchased/{event-id}")
    public HttpEntity<Collection> viewPurchasedTicketsForEvent(@PathVariable("event-id") int eventId,
                                                               @RequestParam("date") LocalDateTime date) {
        return new ResponseEntity<>(bookingFacade.findPurchasedTicketsForEvent(eventId, date), HttpStatus.OK);
    }

    @PostMapping("/tickets")
    public HttpEntity<HttpStatus> bookTickets(@RequestParam("tickets") Set<TicketDTO> tickets) {
        bookingFacade.bookTickets(tickets);
        return ResponseEntity.noContent().build();
    }
}
