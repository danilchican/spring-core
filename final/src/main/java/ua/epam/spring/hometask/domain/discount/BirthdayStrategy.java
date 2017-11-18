package ua.epam.spring.hometask.domain.discount;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

public class BirthdayStrategy implements DiscountStrategy {

    private byte discount;
    private byte allowedDaysDifference;

    @Override
    public byte calculateDiscount(@Nullable User user, @Nonnull Event event,
                                  @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        int dayOfBirthday = user.getBirthday().getDayOfYear();
        int dayOfEvent = airDateTime.getDayOfYear();

        int daysDifference = Math.abs(dayOfBirthday - dayOfEvent);

        if (dayOfBirthday >= dayOfEvent && daysDifference <= allowedDaysDifference) {
            return discount;
        }

        return 0;
    }

    public byte getDiscount() {
        return discount;
    }

    public void setDiscount(byte discount) {
        this.discount = discount;
    }

    public byte getAllowedDaysDifference() {
        return allowedDaysDifference;
    }

    public void setAllowedDaysDifference(byte allowedDaysDifference) {
        this.allowedDaysDifference = allowedDaysDifference;
    }
}
