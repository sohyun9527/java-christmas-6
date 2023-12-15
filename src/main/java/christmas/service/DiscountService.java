package christmas.service;

import christmas.domain.Category;
import christmas.domain.EventDay;
import christmas.domain.OrderedMenus;

public class DiscountService {
    private final int START_DAY = 1;
    private final int DAILY_DISCOUNT = 100;
    private final int START = 1_000;
    private final int DISCOUNT_UNIT = 2_023;
    private final EventDay eventDay;
    private final OrderedMenus orderedMenus;

    public DiscountService(EventDay eventDay, OrderedMenus orderedMenus) {
        this.eventDay = eventDay;
        this.orderedMenus = orderedMenus;
    }

    public int christmas() {
        int discountAmount = 0;
        if (eventDay.isBeforeChristmas()) {
            discountAmount = START + (eventDay.getDay() - START_DAY) * DAILY_DISCOUNT;
        }
        return discountAmount;
    }

    public int weekEnd() {
        int discountAmount = 0;
        if (eventDay.isWeekEnd()) {
            discountAmount = DISCOUNT_UNIT * orderedMenus.getMenuCountByCategory(Category.MAIN);
        }
        return discountAmount;
    }

    public int weekDay() {
        int discountAmount = 0;
        if (!eventDay.isWeekEnd()) {
            discountAmount = DISCOUNT_UNIT * orderedMenus.getMenuCountByCategory(Category.DESSERT);
        }
        return discountAmount;
    }

    public int special() {
        int discountAmount = 0;
        if (eventDay.isSpecial()) {
            discountAmount = START;
        }
        return discountAmount;
    }
}
