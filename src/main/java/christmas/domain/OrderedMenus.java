package christmas.domain;

import christmas.domain.exception.OrderException;
import java.util.List;

public class OrderedMenus {
    private static final int MAX_COUNT = 20;
    private final List<OrderedMenu> menus;

    public OrderedMenus(List<OrderedMenu> menus) {
        this.menus = menus;
    }

    public static OrderedMenus from(List<OrderedMenu> menus) {
        validate(menus);
        return new OrderedMenus(menus);
    }

    public int getTotalAmount() {
        return menus.stream().mapToInt(OrderedMenu::getPrice).sum();
    }

    private static void validate(List<OrderedMenu> menus) {
        validateDuplicateMenu(menus);
        validateOnlyDrink(menus);
        validateOverCount(menus);
    }

    private static void validateOverCount(List<OrderedMenu> menus) {
        int orderMenusCount = menus.stream().mapToInt(OrderedMenu::getCount).sum();

        if (orderMenusCount > MAX_COUNT) {
            throw new IllegalArgumentException("[ERROR] 최대 주문개수는 20개입니다.");
        }
    }

    private static void validateOnlyDrink(List<OrderedMenu> menus) {
        boolean isAllDrinkCategory = menus.stream()
                .allMatch(orderedMenu -> orderedMenu.getCategory() == Category.DRINK);
        if (isAllDrinkCategory) {
            throw new IllegalArgumentException("[ERROR] 음료만 주문할 수 없습니다.");
        }
    }

    private static void validateDuplicateMenu(List<OrderedMenu> menus) {
        if (menus.stream().distinct().count() != menus.size()) {
            throw new OrderException();
        }
    }
}
