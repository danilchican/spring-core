package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.dao.EventDAO;
import ua.epam.spring.hometask.dao.TicketDAO;
import ua.epam.spring.hometask.domain.*;
import ua.epam.spring.hometask.service.BookingService;
import ua.epam.spring.hometask.service.DiscountService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private EventDAO eventDAO;

    @Autowired
    private DiscountService discountService;

    private double vipSeatCoefficient;

    private static Map<EventRating, Double> ratingCoefficients;

    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime,
                                  @Nullable User user, @Nonnull Set<Long> seats) {
        int countSeats = seats.size();

        Auditorium auditorium = eventDAO.findAuditoriumOnDateTime(event.getAuditoriums(), dateTime);

        if (auditorium == null) {
            throw new IllegalArgumentException("There is no auditorium for particular date and time.");
        }

        long countVipSeats = auditorium.countVipSeats(seats);
        long countStandardSeats = countSeats - countVipSeats;

        double eventPriceWithRating = ratingCoefficients.get(event.getRating()) * event.getBasePrice();
        double eventPrice = eventPriceWithRating * (countStandardSeats + countVipSeats * vipSeatCoefficient);

        double eventDiscountPercent = discountService.getDiscount(user, event, dateTime, countSeats);

        return calculateEventPriceByDiscountPercent(eventPrice, eventDiscountPercent);
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            ticketDAO.save(ticket);
        }
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        return ticketDAO.getPurchasedTicketsForEvent(event, dateTime);
    }

    public void setVipSeatCoefficient(double vipSeatCoefficient) {
        this.vipSeatCoefficient = vipSeatCoefficient;
    }

    public void setRatingCoefficients(Map<EventRating, Double> ratingCoefficientsMap) {
        ratingCoefficients = ratingCoefficientsMap;
    }

    private double calculateEventPriceByDiscountPercent(double eventPrice, double eventDiscountPercent) {
        double eventDiscount = eventPrice * eventDiscountPercent / 100;

        return eventPrice - eventDiscount;
    }
}
