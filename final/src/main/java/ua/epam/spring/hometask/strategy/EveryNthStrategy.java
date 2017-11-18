package ua.epam.spring.hometask.strategy;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

public class EveryNthStrategy implements DiscountStrategy {

    private int nthTicketNumber;
    private double discount;

    @Override
    public double calculateDiscount(@Nullable User user, @Nonnull Event event,
                                    @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        long bonusTickets = 0;

        for (long i = 1; i <= numberOfTickets; i++) {
            if (i % nthTicketNumber == 0) {
                bonusTickets++;
            }
        }

        return discount * bonusTickets / numberOfTickets;
    }

    public int getNthTicketNumber() {
        return nthTicketNumber;
    }

    public void setNthTicketNumber(int nthTicketNumber) {
        this.nthTicketNumber = nthTicketNumber;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
