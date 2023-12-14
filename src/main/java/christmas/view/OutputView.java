package christmas.view;

import christmas.domain.Badge;
import christmas.domain.order.OrderedMenu;
import christmas.repository.MenuBoard;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class OutputView {
    private static final String START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String PREVIEW_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";

    public void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public void printTotalAmount(long price) {
        System.out.println("\n<할인 전 총주문 금액>");
        System.out.println(moneyFormatter(price));
    }

    public void printPreviewMessage(int day) {
        System.out.printf((PREVIEW_MESSAGE) + "%n", day);
    }

    public void printOrderedMenus(List<OrderedMenu> menus) {
        System.out.println();
        System.out.println("<주문 메뉴>");
        for (OrderedMenu menu : menus) {
            System.out.println(menu.getName() + " " + menu.getQuantity() + "개");
        }
    }

    private String moneyFormatter(long price) {
        NumberFormat format = NumberFormat.getInstance(Locale.KOREA);

        return format.format(price) + "원";
    }

    public void printTotalBenefitAmount(int price) {
        System.out.println("\n<총혜택 금액>");
        StringBuilder stringBuilder = new StringBuilder();
        if (price != 0) {
            stringBuilder.append("-");
        }
        stringBuilder.append(moneyFormatter(price));
        System.out.println(stringBuilder);
    }

    public void printPromotionResult(MenuBoard menuBoard) {
        System.out.println("\n<증정 메뉴>");
        StringBuilder sb = new StringBuilder();
        sb.append(menuBoard.getName());
        if (menuBoard != MenuBoard.NONE) {
            sb.append(" 1개");
        }
        System.out.println(sb);
    }

    public void printDiscountDetail(String type, int amount) {
        System.out.print(type + ": ");
        System.out.println("-" + moneyFormatter(amount));
    }

    public void printDiscountTitle() {
        System.out.println("\n<혜택 내역>");
    }

    public void printBadgeResult(Badge badge) {
        System.out.println("\n<12월 이벤트 배지>");
        System.out.println(badge.getBadge());
    }

    public void printNone() {
        System.out.println("없음");
    }

    public void printAfterDiscountAmount(int price) {
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.println(moneyFormatter(price));
    }
}
