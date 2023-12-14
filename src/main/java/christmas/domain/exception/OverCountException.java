package christmas.domain.exception;

import christmas.view.message.ErrorMessage;

public class OverCountException extends IllegalArgumentException {
    public OverCountException(int count) {
        super(String.format(ErrorMessage.OVER_COUNT.getMessage(), count));
    }
}
