package christmas.service;

import christmas.domain.BenefitType;
import christmas.domain.Category;
import christmas.domain.EventDay;
import christmas.domain.MenuBoard;
import christmas.domain.OrderedMenus;
import java.util.EnumMap;

public class DiscountService {
    private static final int START_DAY = 1;
    private static final int DAILY_DISCOUNT = 100;
    private static final int START = 1_000;
    private static final int DISCOUNT_UNIT = 2_023;
    private static final int MINIMUM_PRICE = 10_000;
    private static final int PROMOTION_PRICE = 120_000;
    private final EventDay eventDay;
    private final OrderedMenus orderedMenus;

    public DiscountService(EventDay eventDay, OrderedMenus orderedMenus) {
        this.eventDay = eventDay;
        this.orderedMenus = orderedMenus;
    }

    public EnumMap<BenefitType, Integer> getResult() {
        EnumMap<BenefitType, Integer> result = new EnumMap<>(BenefitType.class);
        if (isAbleGetBenefit()) { // 최소 혜택 적용 가능 가격
            result.put(BenefitType.CHRISTMAS, christmas());
            result.put(BenefitType.WEEKDAY, weekDay());
            result.put(BenefitType.WEEKEND, weekEnd());
            result.put(BenefitType.SPECIAL, special());
        }
        if (isAbleGetPromotion()) {
            result.put(BenefitType.PROMOTION, MenuBoard.CHAMPAGNE.getPrice());
        }
        return result;
    }

    public boolean isAbleGetBenefit() {
        return orderedMenus.getTotalAmount() >= MINIMUM_PRICE;
    }

    public boolean isAbleGetPromotion() {
        return orderedMenus.getTotalAmount() >= PROMOTION_PRICE;
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

    // 할인 금액, 샴페인 가격 뺀거
    public int getTotalDiscountAmount() {
        return christmas() + weekDay() + weekEnd() + special();
    }

    public int getAfterDiscountAmount() {
        return orderedMenus.getTotalAmount() - getTotalDiscountAmount();
    }
}
