package christmas.domain.exception;

import christmas.repository.Category;
import christmas.view.message.ErrorMessage;

public class OnlyDrinkException extends IllegalArgumentException {
    public OnlyDrinkException(Category category) {
        super(String.format(ErrorMessage.INVALID_ONLY_CATEGORY.getMessage(), category.getMessage()));
    }
}
