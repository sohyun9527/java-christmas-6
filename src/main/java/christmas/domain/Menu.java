package christmas.domain;

import java.util.Arrays;
import java.util.Objects;

public class Menu {
    private final String name;
    private final Category category;
    private final int price;

    public Menu(String name, Category category, int price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public static Menu of(String name) {
        return Arrays.stream(MenuBoard.values())
                .filter(menuBoard -> menuBoard.getName().equals(name))
                .findFirst()
                .map(menuBoard -> new Menu(name, menuBoard.getCategory(), menuBoard.getPrice()))
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 메뉴입니다."));
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Menu menu = (Menu) o;
        return Objects.equals(name, menu.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
