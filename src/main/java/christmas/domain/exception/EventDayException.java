package christmas.domain.exception;

import christmas.view.ErrorMessage;

public class EventDayException extends IllegalArgumentException {
    public EventDayException() {
        super(ErrorMessage.INVALID_DAY.getMessage());
    }
}
