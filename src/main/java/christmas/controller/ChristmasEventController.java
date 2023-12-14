package christmas.controller;

import christmas.domain.Badge;
import christmas.domain.Condition;
import christmas.domain.EventDay;
import christmas.domain.benefit.BenefitResult;
import christmas.domain.benefit.Discount;
import christmas.domain.benefit.DiscountType;
import christmas.domain.order.OrderInput;
import christmas.domain.order.OrderedMenu;
import christmas.domain.order.OrderedMenus;
import christmas.repository.MenuBoard;
import christmas.util.ReadUntilValid;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.EnumMap;
import java.util.List;

public class ChristmasEventController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasEventController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        EventDay eventDay = getVisitDay();

        // 탈출 조건 -> 재시작하지 않는다 || 금액이 10,000원을 넘었다.
        boolean condition = true;
        while (condition) {
            OrderedMenus orderedMenus = generateOrderedMenus();
            Discount discount = new Discount(orderedMenus, eventDay);
            BenefitResult benefitResult = new BenefitResult(discount.getResult());
            showOrderResult(eventDay, orderedMenus, discount);
            condition = showDiscountResult(discount, benefitResult, orderedMenus);
        }

    }

    private OrderedMenus generateOrderedMenus() {
        return ReadUntilValid.readUntilValid(() -> {
            String orderMenuNames = inputView.readOrder();
            List<String> nameAndQuantity = List.of(orderMenuNames.split(",", -1));
            List<OrderInput> orderInputs = getOrderInputs(nameAndQuantity);
            List<OrderedMenu> menus = generateOrderedMenus(orderInputs);
            return new OrderedMenus(menus);
        });
    }

    private static List<OrderedMenu> generateOrderedMenus(List<OrderInput> orderInputs) {
        return orderInputs.stream().map(OrderInput::generateOrderedMenu).toList();
    }

    private static List<OrderInput> getOrderInputs(List<String> nameAndQuantity) {
        return nameAndQuantity.stream().map(OrderInput::from).toList();
    }

    private EventDay getVisitDay() {
        return ReadUntilValid.readUntilValid(() ->
                EventDay.from(inputView.readVisitDay())
        );
    }

    private void showBenefitAmount(Discount discount, BenefitResult benefitResult, OrderedMenus orderedMenus) {
        int totalBenefitAmount = benefitResult.benefitAmount();
        int totalOrderAmount = orderedMenus.getTotalAmount();
        int totalDiscountAmount = discount.getTotalDiscount();

        outputView.printTotalBenefitAmount(totalBenefitAmount);
        outputView.printAfterDiscountAmount(totalOrderAmount - totalDiscountAmount);
        outputView.printBadgeResult(Badge.getByAmount(totalBenefitAmount));
    }

    private boolean showDiscountResult(Discount discount, BenefitResult benefitResult, OrderedMenus menus) {
        outputView.printDiscountTitle();
        EnumMap<DiscountType, Integer> result = benefitResult.getBenefitResult();
        // 만원 이상이야? 혜택 적용된게 하나라도 있어?
        if (isAppliedBenefit(discount, benefitResult.isBenefitIsEmpty())) {
            return true;
        }
        for (DiscountType type : DiscountType.values()) {
            int discountAmount = result.getOrDefault(type, 0);
            if (discountAmount != 0) {
                outputView.printDiscountDetail(type.getType(), discountAmount);
            }
        }
        showBenefitAmount(discount, benefitResult, menus);
        return false;
    }

    private boolean isAppliedBenefit(Discount discount, boolean resultIsZero) {
        if (!discount.isOverMinimumAmount() || resultIsZero) {
            outputView.printNone();
            return reOrderQuestion();
        }
        return false;
    }

    private boolean reOrderQuestion() {
        while (true) {
            try {
                Condition condition = Condition.from(inputView.readCondition());
                return condition == Condition.RESTART;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void showOrderResult(EventDay eventDay, OrderedMenus orderedMenus, Discount discount) {
        long totalAmount = orderedMenus.getTotalAmount();
        MenuBoard menu = MenuBoard.NONE;

        outputView.printPreviewMessage(eventDay.getDay());
        outputView.printOrderedMenus(orderedMenus.getMenus());
        outputView.printTotalAmount(totalAmount);
        if (discount.isOverPromotionPrice()) {
            menu = MenuBoard.CHAMPAGNE;
        }
        outputView.printPromotionResult(menu);
    }
}
