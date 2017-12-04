package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.repository.AirDateRepository;
import ua.epam.spring.hometask.repository.SeatRepository;
import ua.epam.spring.hometask.repository.TicketRepository;
import ua.epam.spring.hometask.domain.*;
import ua.epam.spring.hometask.service.BookingService;
import ua.epam.spring.hometask.service.DiscountService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private AirDateRepository airDateRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private DiscountService discountService;

    private double vipSeatCoefficient;

    private static Map<EventRating, Double> ratingCoefficients;

    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime,
                                  @Nullable User user, @Nonnull Set<Long> seats) {
        Auditorium auditorium = airDateRepository.findByEventIdAndDateTime(event.getId(), dateTime);

        if (auditorium == null) {
            throw new IllegalArgumentException("There is no auditorium for particular date and time.");
        }

        return calculateEventPriceByDiscountPercent(user, event, auditorium, seats, dateTime);
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {
        ticketRepository.save(tickets);
    }

    @Nonnull
    @Override
    public List<Ticket> getPurchasedTicketsForEvent(long eventId, @Nonnull LocalDateTime dateTime) {
        return ticketRepository.findByAirDateEventIdAndAirDateDateTime(eventId, dateTime);
    }

    /**
     * Calculate event price by discount percent.
     *
     * @param user       User that buys ticket could be needed to calculate discount.
     * @param event      Event to get base ticket price, vip seats and other
     *                   information
     * @param auditorium Auditorium for particular date and time
     * @param dateTime   Date and time of event air
     *                   Can be <code>null</code>
     * @param seats      Set of seat numbers that user wants to buy
     * @return Event price with discount
     */
    private double calculateEventPriceByDiscountPercent(User user, Event event, Auditorium auditorium,
                                                        Set<Long> seats, LocalDateTime dateTime) {
        int countSeats = seats.size();

        double eventPrice = calculateEventPrice(event, auditorium, countSeats);
        double eventDiscountPercent = discountService.getDiscount(user, event, dateTime, countSeats);
        double eventDiscount = eventPrice * eventDiscountPercent / 100;

        return eventPrice - eventDiscount;
    }

    /**
     * Calculate event price.
     *
     * @param event      Calculate price for event
     * @param auditorium Auditorium to get count of vip seats
     * @param countSeats Count of all seats in auditorium
     * @return event price
     */
    private double calculateEventPrice(@Nonnull Event event, @Nonnull Auditorium auditorium, int countSeats) {
        long countVipSeats = seatRepository.countByAuditoriumIdAndVipIsTrue(auditorium.getId());
        long countStandardSeats = countSeats - countVipSeats;

        double eventPriceWithRating = ratingCoefficients.get(event.getRating()) * event.getBasePrice();

        return eventPriceWithRating * (countStandardSeats + countVipSeats * vipSeatCoefficient);
    }

    public void setVipSeatCoefficient(double vipSeatCoefficient) {
        this.vipSeatCoefficient = vipSeatCoefficient;
    }

    public void setRatingCoefficients(Map<EventRating, Double> ratingCoefficientsMap) {
        ratingCoefficients = ratingCoefficientsMap;
    }
}
