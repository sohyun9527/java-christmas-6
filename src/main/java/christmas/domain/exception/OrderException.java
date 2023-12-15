package christmas.domain.exception;

import christmas.view.ErrorMessage;

public class OrderException extends IllegalArgumentException {
    public OrderException() {
        super(ErrorMessage.INVALID_ORDER.getMessage());
    }
}
