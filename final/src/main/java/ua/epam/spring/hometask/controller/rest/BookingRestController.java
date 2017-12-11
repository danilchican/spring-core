package ua.epam.spring.hometask.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.epam.spring.hometask.dto.TicketDTO;
import ua.epam.spring.hometask.facade.BookingFacade;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

import static ua.epam.spring.hometask.validator.CommonValidator.DATETIME_REGEX;

@RestController
@RequestMapping("/api/booking")
@Validated
public class BookingRestController {

    @Autowired
    private BookingFacade bookingFacade;

    @RequestMapping(value = "/purchased/{event-id}", method = RequestMethod.GET)
    public HttpEntity<Collection> viewPurchasedTicketsForEvent(
            @Pattern(regexp = "\\d", message = "Event id should be a number")
            @PathVariable("event-id") int eventId,
            @Pattern(regexp = DATETIME_REGEX, message = "Date should be like 0000-00-00 00:00:00")
            @RequestParam("date") LocalDateTime date) {
        return new ResponseEntity<>(bookingFacade.findPurchasedTicketsForEvent(eventId, date), HttpStatus.OK);
    }

    @RequestMapping(value = "/tickets", method = RequestMethod.POST)
    public HttpEntity<HttpStatus> bookTickets(@RequestParam("tickets") Set<TicketDTO> tickets) {
        bookingFacade.bookTickets(tickets);
        return ResponseEntity.noContent().build();
    }
}
