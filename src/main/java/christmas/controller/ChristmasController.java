package christmas.controller;

import christmas.domain.Badge;
import christmas.domain.BenefitType;
import christmas.domain.EventDay;
import christmas.domain.MenuBoard;
import christmas.domain.OrderedMenu;
import christmas.domain.OrderedMenus;
import christmas.domain.exception.EventDayException;
import christmas.domain.exception.OrderException;
import christmas.service.DiscountService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class ChristmasController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChristmasController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartMessage();
        EventDay eventDay = getEventDay();
        OrderedMenus orderedMenus = getOrderedMenus();
        DiscountService discountService = new DiscountService(eventDay, orderedMenus);

        showOrderResult(eventDay, orderedMenus);
        showBenefitAndPromotionResult(discountService);
        showDiscountAmount(discountService);
    }

    private void showDiscountAmount(DiscountService discountService) {
        outputView.printTotalBenefitAmount(discountService.getTotalBenefit());
        outputView.printAfterDiscountAmount(discountService.getAfterDiscountAmount());
        outputView.printBadge(Badge.getByPrice(discountService.getTotalBenefit()));
    }

    private void showBenefitAndPromotionResult(DiscountService discountService) {
        if (discountService.isAbleGetPromotion()) {
            outputView.printPromotionResult(MenuBoard.CHAMPAGNE);
        }
        outputView.printPromotionResult(MenuBoard.NONE);
        showBenefitDetail(discountService);
    }

    private void showOrderResult(EventDay eventDay, OrderedMenus orderedMenus) {
        outputView.printPreviewMessage(eventDay.getDay());
        outputView.printOrderMenus(orderedMenus.getMenus());
        outputView.printTotalAmount(orderedMenus.getTotalAmount());
    }

    private OrderedMenus getOrderedMenus() {
        while (true) {
            try {

                String input = inputView.readMenuAndCount();
                List<String> manyMenuAndCount = List.of(input.split(",", -1));
                List<OrderedMenu> orderedMenuList = new ArrayList<>();
                for (String nameAndQuantity : manyMenuAndCount) {
                    orderedMenuList.add(OrderedMenu.from(nameAndQuantity));
                }
                return OrderedMenus.from(orderedMenuList);
            } catch (OrderException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private EventDay getEventDay() {
        while (true) {
            try {
                return EventDay.from(inputView.readVisitDay());
            } catch (EventDayException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void showBenefitDetail(DiscountService discountService) {
        outputView.printBenefitTitle();
        EnumMap<BenefitType, Integer> result = discountService.getResult();
        if (result.isEmpty()) {
            System.out.println("없음");
            return;
        }
        for (BenefitType value : BenefitType.values()) {
            int discountAmount = result.getOrDefault(value, 0);
            if (discountAmount != 0) {
                outputView.printBenefitDetail(value.getType(), result.getOrDefault(value, 0));
            }
        }
    }
}
