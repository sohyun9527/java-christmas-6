package christmas.domain;

import christmas.domain.exception.OrderException;
import christmas.repository.MenuBoard;
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
        List<String> nameAndQuantity = List.of(input.split(NAME_QUANTITY_DIVIDER, -1));
        validateSize(nameAndQuantity);

        String name = nameAndQuantity.get(NAME_IDX);
        String quantity = nameAndQuantity.get(QUANTITY_IDX);
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


    // name을 갖고 MenuBoard 찾고, quantity와 함께 랩핑해야된다
    public OrderedMenu generateOrderedMenu() {
        MenuBoard menuBoard = MenuBoard.from(name); // 실제 있는 메뉴인지 검증

        return new OrderedMenu(menuBoard, quantity);
    }
}
