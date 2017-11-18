package ua.epam.spring.hometask.strategy;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

public class EveryCertainTicketStrategy implements DiscountStrategy {

    private int ticketNumberToGetDiscount;
    private double discount;

    @Override
    public double calculateDiscount(@Nullable User user, @Nonnull Event event,
                                    @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        long bonusTickets = 0;

        for (long currentTicketNumber = 1; currentTicketNumber <= numberOfTickets; currentTicketNumber++) {
            if (ticketNumberHasDiscount(currentTicketNumber)) {
                bonusTickets++;
            }
        }

        return discount * bonusTickets / numberOfTickets;
    }

    public int getTicketNumberToGetDiscount() {
        return ticketNumberToGetDiscount;
    }

    public void setTicketNumberToGetDiscount(int ticketNumberToGetDiscount) {
        this.ticketNumberToGetDiscount = ticketNumberToGetDiscount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    private boolean ticketNumberHasDiscount(long currentTicketNumber) {
        return currentTicketNumber % ticketNumberToGetDiscount == 0;
    }
}
