package christmas.domain.exception;

import christmas.view.message.ErrorMessage;

public class DayException extends IllegalArgumentException {
    public DayException() {
        super(ErrorMessage.INVALID_DAY.getMessage());
    }
}
