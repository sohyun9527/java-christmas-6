package christmas.domain.order;

import christmas.repository.Category;
import christmas.repository.MenuBoard;
import java.util.Objects;

public class OrderedMenu {
    private final MenuBoard menuBoard;
    private final int quantity;

    public OrderedMenu(MenuBoard menu, int quantity) {
        this.menuBoard = menu;
        this.quantity = quantity;
    }

    // 주문된 메뉴 하나 가격
    public int getAmount() {
        return menuBoard.getPrice() * quantity;
    }

    public String getName() {
        return menuBoard.getName();
    }

    public Category getCategory() {
        return menuBoard.getCategory();
    }

    public MenuBoard getMenuBoard() {
        return menuBoard;
    }

    public int getQuantity() {
        return quantity;
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
        return getMenuBoard() == that.getMenuBoard();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMenuBoard());
    }
}
