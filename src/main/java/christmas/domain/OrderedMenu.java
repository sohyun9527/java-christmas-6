package christmas.domain;

import christmas.domain.exception.OrderException;
import java.util.List;
import java.util.Objects;

public class OrderedMenu {
    private static final int MINIMUM_COUNT = 1;
    private static final int VALID_SIZE = 2;
    private static final String NAME_AND_COUNT_DELIMITER = "-";
    private final Menu menu;
    private final int count;

    public OrderedMenu(Menu menu, int count) {
        this.menu = menu;
        this.count = count;
    }

    public static OrderedMenu from(String input) {
        List<String> nameAndCount = List.of(input.split(NAME_AND_COUNT_DELIMITER, -1));
        validate(nameAndCount);
        Menu menu = Menu.of(nameAndCount.get(0));
        int count = Integer.parseInt(nameAndCount.get(1));

        return new OrderedMenu(menu, count);
    }

    public int getPrice() {
        return menu.getPrice() * count;
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return menu.getName();
    }

    public Category getCategory() {
        return menu.getCategory();
    }

    private static void validate(List<String> nameAndCount) {
        String count = nameAndCount.get(1);
        validateInputSize(nameAndCount);
        validateCount(count);
    }

    private static void validateCount(String count) {
        validateOnlyDigit(count);
        validateMinimumCount(count);
    }

    private static void validateMinimumCount(String count) {
        if (Integer.parseInt(count) < MINIMUM_COUNT) {
            throw new OrderException();
        }
    }

    private static void validateOnlyDigit(String count) {
        if (!count.chars().allMatch(Character::isDigit)) {
            throw new OrderException();
        }
    }


    private static void validateInputSize(List<String> nameAndCount) {
        if (nameAndCount.size() != VALID_SIZE) {
            throw new OrderException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderedMenu that = (OrderedMenu) o;
        return Objects.equals(menu, that.menu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu);
    }
}
