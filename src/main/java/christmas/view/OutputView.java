package christmas.view;

import christmas.domain.Badge;
import christmas.domain.MenuBoard;
import christmas.domain.OrderedMenu;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class OutputView {
    private static final String START_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private static final String PREVIEW_MESSAGE = "12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";


    public void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public void printPreviewMessage(int day) {
        System.out.printf((PREVIEW_MESSAGE) + "%n", day);
    }

    public void printOrderMenus(List<OrderedMenu> menu) {
        System.out.println("<주문 메뉴>");
        for (OrderedMenu orderedMenu : menu) {
            menuFormatPrinter(orderedMenu.getName(), orderedMenu.getCount());
        }
    }

    private static void menuFormatPrinter(String name, int count) {
        System.out.println(name + " " + count + "개");
    }

    public void printPromotionResult(MenuBoard menuBoard) {
        System.out.println("<증정 메뉴>");
        if (menuBoard == MenuBoard.NONE) {
            System.out.println(menuBoard.getName());
            return;
        }
        menuFormatPrinter(menuBoard.getName(), 1);
    }

    public void printBenefitDetail(String type, int discountAmount) {
        System.out.println(type + "-" + moneyFormatter(discountAmount));
    }

    private String moneyFormatter(int discountAmount) {
        NumberFormat formatter = NumberFormat.getInstance(Locale.KOREA);
        return formatter.format(discountAmount) + "원";
    }

    public void printBenefitTitle() {
        System.out.println("<혜택 내역>");
    }

    public void printTotalBenefitAmount(int totalDiscountAmount) {
        String result = "";
        System.out.println("<총혜택 금액>");
        if (totalDiscountAmount != 0) {
            result += "-";
        }
        System.out.println(result + moneyFormatter(totalDiscountAmount));
    }

    public void printAfterDiscountAmount(int afterDiscountAmount) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(moneyFormatter(afterDiscountAmount));
    }

    public void printBadge(Badge badge) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badge.getBadge());
    }

    public void printTotalAmount(int price) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(moneyFormatter(price));
    }
}
