package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String REQUEST_VISIT_DAY = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String REQUEST_MENU_AND_COUNT = "주문하실 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";
    private static final String REQUEST_COMMAND = "주문을 다시 할까요? Q / R";

    public String readCondition() {
        System.out.println(REQUEST_COMMAND);
        String input = getUserInput();
        validateEmptyLine(input);

        return input;
    }

    public String readVisitDay() {
        System.out.println(REQUEST_VISIT_DAY);
        String input = getUserInput();
        validateEmptyLine(input);

        return input;
    }

    public String readOrder() {
        System.out.println(REQUEST_MENU_AND_COUNT);
        String input = getUserInput();
        validateEmptyLine(input);

        return input;
    }

    private void validateEmptyLine(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 아무것도 입력하지 않았습니다.");
        }
    }

    private String getUserInput() {
        return Console.readLine();
    }
}
