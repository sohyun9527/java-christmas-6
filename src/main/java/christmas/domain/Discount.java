package christmas.domain;

import christmas.repository.Category;
import christmas.repository.MenuBoard;
import java.util.EnumMap;

public class Discount {
    private static final int MINIMUM_AMOUNT = 10_000;
    private static final int START_AMOUNT = 1_000;
    private static final int DAILY_DISCOUNT = 100;
    private static final int DISCOUNT_UNIT = 2_023;
    private static final int PROMOTION_PRICE = 120_000;
    private final OrderedMenus menus;
    private final EventDay eventDay;

    public Discount(OrderedMenus menus, EventDay eventDay) {
        this.menus = menus;
        this.eventDay = eventDay;
    }

    public EnumMap<DiscountType, Integer> getResult() {
        EnumMap<DiscountType, Integer> result = new EnumMap<>(DiscountType.class);
        if (isOverMinimumAmount()) {
            result.put(DiscountType.CHRISTMAS, christmas());
            result.put(DiscountType.WEEKDAY, weekDay());
            result.put(DiscountType.WEEKEND, weekEnd());
            result.put(DiscountType.SPECIAL, special());
        }
        if (isOverPromotionPrice()) {
            result.put(DiscountType.PROMOTION, MenuBoard.CHAMPAGNE.getPrice());
        }
        return result;
    }

    public boolean isOverPromotionPrice() {
        return menus.getTotalAmount() >= PROMOTION_PRICE;
    }

    public int christmas() {
        int discountAmount = 0;
        if (eventDay.isBeforeChristmas()) {
            discountAmount += START_AMOUNT + (eventDay.getDay() - 1) * DAILY_DISCOUNT;
        }
        return discountAmount;
    }

    public int weekDay() {
        int discountAmount = 0;
        if (!eventDay.isWeekEnd()) {
            int totalDessertCount = menus.getCategoryMenuCount(Category.DESSERT);
            discountAmount += totalDessertCount * DISCOUNT_UNIT;
        }
        return discountAmount;
    }

    public int weekEnd() {
        int discountAmount = 0;
        if (eventDay.isWeekEnd()) {
            int totalDessertCount = menus.getCategoryMenuCount(Category.MAIN);
            discountAmount += totalDessertCount * DISCOUNT_UNIT;
        }
        return discountAmount;
    }

    public int special() {
        int discountAmount = 0;
        if (eventDay.isSpecial()) {
            discountAmount += START_AMOUNT;
        }
        return discountAmount;
    }

    public boolean isOverMinimumAmount() {
        return menus.getTotalAmount() >= MINIMUM_AMOUNT;
    }
}