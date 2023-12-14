package christmas.domain;

import christmas.domain.exception.OrderException;
import java.util.List;

public class OrderInput {
    private static final int MINIMUM_COUNT = 1;
    private static final int NAME_IDX = 0;
    private static final int QUANTITY_IDX = 1;
    private static final String NAME_QUANTITY_DIVIDER = "-";
    private static final int VALID_SIZE = 2;
    private final String name;
    private final int quantity;

    public OrderInput(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public static OrderInput from(String input) {
        List<String> nameAndQuantity = List.of(input.split("-", -1));
        String name = nameAndQuantity.get(NAME_IDX);
        String quantity = nameAndQuantity.get(QUANTITY_IDX);

        validateSize(nameAndQuantity);
        int count = validateQuantityOnlyDigit(quantity);
        validateQuantityCount(count);

        return new OrderInput(name, count);
    }

    private static void validateSize(List<String> nameAndQuantity) {
        if (nameAndQuantity.size() != VALID_SIZE) {
            throw new OrderException();
        }
    }

    private static int validateQuantityOnlyDigit(String input) {
        if (!input.chars().allMatch(Character::isDigit)) {
            throw new OrderException();
        }
        return Integer.parseInt(input);
    }

    private static void validateQuantityCount(int quantity) {
        if (quantity < MINIMUM_COUNT) {
            throw new OrderException();
        }
    }
}
