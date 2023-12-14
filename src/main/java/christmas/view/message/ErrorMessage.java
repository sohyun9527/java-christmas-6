package christmas.view.message;

public enum ErrorMessage {
    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_DAY("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ONLY_CATEGORY("%s만 주문할 수 없습니다. 다시 입력해 주세요."),
    OVER_COUNT("%d개를 초과해서 주문할 수 없습니다. 다시 입력해 주세요.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return "[ERROR] " + message;
    }
}
