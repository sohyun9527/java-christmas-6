package christmas.domain;

import christmas.domain.exception.OnlyDrinkException;
import christmas.domain.exception.OrderException;
import christmas.repository.Category;
import java.util.List;

public class OrderedMenus {
    // 중복 검수, 총합이 20개 넘는지 검수
    private final List<OrderedMenu> menus;

    public OrderedMenus(List<OrderedMenu> menus) {
        validateOrderedMenus(menus);
        this.menus = menus;
    }

    private void validateOrderedMenus(List<OrderedMenu> menus) {
        validateDuplicate(menus);
        validateIsOnlyDrinks(menus);
        validateIsOverCount(menus);
    }

    private void validateIsOverCount(List<OrderedMenu> menus) {
    }

    private void validateIsOnlyDrinks(List<OrderedMenu> menus) {
        boolean isAllDrinks = menus.stream()
                .allMatch(orderedMenu -> orderedMenu.getCategory() == Category.BEVERAGE);

        if (isAllDrinks) {
            throw new OnlyDrinkException(Category.BEVERAGE);
        }
    }

    private void validateDuplicate(List<OrderedMenu> menus) {
        if (menus.stream().distinct().count() != menus.size()) {
            throw new OrderException();
        }
    }
}
