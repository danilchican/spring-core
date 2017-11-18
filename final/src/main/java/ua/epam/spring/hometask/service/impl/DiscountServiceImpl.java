package ua.epam.spring.hometask.service.impl;

import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.domain.discount.DiscountStrategy;
import ua.epam.spring.hometask.service.DiscountService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {

    private List<DiscountStrategy> discountStrategies;

    public void setDiscountStrategies(List<DiscountStrategy> discountStrategies) {
        this.discountStrategies = discountStrategies;
    }

    @Override
    public byte getDiscount(@Nullable User user, @Nonnull Event event,
                            @Nonnull LocalDateTime airDateTime, long numberOfTickets) {
        byte maxDiscount = 0;

        for (DiscountStrategy discountStrategy : discountStrategies) {
            byte currentDiscountOfStrategy = discountStrategy.calculateDiscount(user, event, airDateTime, numberOfTickets);

            if (currentDiscountOfStrategy > maxDiscount) {
                maxDiscount = currentDiscountOfStrategy;
            }
        }

        return maxDiscount;
    }
}
