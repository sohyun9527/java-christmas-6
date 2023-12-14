package christmas.controller;

import christmas.domain.Badge;
import christmas.domain.Discount;
import christmas.domain.DiscountType;
import christmas.domain.EventDay;
import christmas.domain.OrderInput;
import christmas.domain.OrderedMenu;
import christmas.domain.OrderedMenus;
import christmas.repository.MenuBoard;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.ArrayList;
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
        EventDay eventDay = EventDay.from(inputView.readVisitDay());
        outputView.printPreviewMessage(eventDay.getDay());
        String orderMenuNames = inputView.readOrder();
        List<String> nameAndQuantity = List.of(orderMenuNames.split(",", -1));
        List<OrderInput> orderInputs = new ArrayList<>();
        for (String input : nameAndQuantity) {
            orderInputs.add(OrderInput.from(input));
        }
        List<OrderedMenu> menus = new ArrayList<>();
        for (OrderInput orderInput : orderInputs) {
            menus.add(orderInput.generateOrderedMenu());
        }
        OrderedMenus orderedMenus = new OrderedMenus(menus);
        Discount discount = new Discount(orderedMenus, eventDay);
        showOrderResult(orderedMenus, discount);
        showDiscountResult(discount);
        showBenefitAmount(discount, orderedMenus);

    }

    private void showBenefitAmount(Discount discount, OrderedMenus orderedMenus) {
        int totalBenefitAmount = discount.getResult().values().stream().mapToInt(i -> i).sum();
        int totalOrderAmount = orderedMenus.getTotalAmount();
        int totalDiscountAmount = discount.getTotalDiscount();

        outputView.printTotalBenefitAmount(totalBenefitAmount);
        outputView.printAfterDiscountAmount(totalOrderAmount - totalDiscountAmount);
        outputView.printBadgeResult(Badge.getByAmount(totalBenefitAmount));
    }

    private void showDiscountResult(Discount discount) {
        outputView.printDiscountTitle();
        EnumMap<DiscountType, Integer> result = discount.getResult();
        if (result.isEmpty()) {
            outputView.printNone();
            return;
        }
        for (DiscountType type : DiscountType.values()) {
            int discountAmount = result.getOrDefault(type, 0);
            if (discountAmount != 0) {
                outputView.printDiscountDetail(type.getType(), discountAmount);
            }
        }
    }

    private void showOrderResult(OrderedMenus orderedMenus, Discount discount) {
        long totalAmount = orderedMenus.getTotalAmount();
        MenuBoard menu = MenuBoard.NONE;

        outputView.printOrderedMenus(orderedMenus.getMenus());
        outputView.printTotalAmount(totalAmount);
        if (discount.isOverPromotionPrice()) {
            menu = MenuBoard.CHAMPAGNE;
        }
        outputView.printPromotionResult(menu);
    }
}
