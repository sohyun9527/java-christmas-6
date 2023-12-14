package christmas.domain;

import christmas.domain.exception.OnlyDrinkException;
import christmas.domain.exception.OrderException;
import christmas.domain.exception.OverCountException;
import christmas.repository.Category;
import java.util.List;

public class OrderedMenus {
    // 중복 검수, 총합이 20개 넘는지 검수
    private static final int MAX_COUNT = 20;
    private final List<OrderedMenu> menus;

    public OrderedMenus(List<OrderedMenu> menus) {
        validateOrderedMenus(menus);
        this.menus = menus;
    }

    public long getTotalAmount() {
        long amount = 0;
        for (OrderedMenu menu : menus) {
            amount += menu.getAmount();
        }
        return amount;
    }

    public int getCategoryMenuCount(Category category) {
        return menus.stream()
                .filter(menu -> menu.getCategory() == category)
                .mapToInt(OrderedMenu::getQuantity)
                .sum();
    }

    private void validateOrderedMenus(List<OrderedMenu> menus) {
        validateDuplicate(menus);
        validateIsOnlyDrinks(menus);
        validateIsOverCount(menus);
    }

    private void validateIsOverCount(List<OrderedMenu> menus) {
        int count = 0;
        for (OrderedMenu menu : menus) {
            count += menu.getQuantity();
        }
        if (count > MAX_COUNT) {
            throw new OverCountException(MAX_COUNT);
        }
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

    public List<OrderedMenu> getMenus() {
        return menus;
    }
}
