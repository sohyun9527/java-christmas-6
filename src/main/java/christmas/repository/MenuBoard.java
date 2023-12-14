package christmas.repository;

import christmas.domain.exception.OrderException;
import java.util.Arrays;

public enum MenuBoard {
    MUSHROOM_SOUP(Category.APPETIZER, "양송이수프", 6_000),
    TAPAS(Category.APPETIZER, "타파스", 5_500),
    CAESAR_SALAD(Category.APPETIZER, "시저샐러드", 8_000),

    // 메인
    T_BONE_STEAK(Category.MAIN, "티본스테이크", 55_000),
    BBQ_RIB(Category.MAIN, "바비큐립", 54_000),
    SEAFOOD_PASTA(Category.MAIN, "해산물파스타", 35_000),
    CHRISTMAS_PASTA(Category.MAIN, "크리스마스파스타", 25_000),

    // 디저트
    CHOCO_CAKE(Category.DESSERT, "초코케이크", 15_000),
    ICE_CREAM(Category.DESSERT, "아이스크림", 5_000),

    // 음료
    ZERO_COLA(Category.BEVERAGE, "제로콜라", 3_000),
    RED_WINE(Category.BEVERAGE, "레드와인", 60_000),
    CHAMPAGNE(Category.BEVERAGE, "샴페인", 25_000);

    private final Category category;
    private final String name;
    private final int price;

    MenuBoard(Category category, String name, int price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public static MenuBoard from(String name) {
        return Arrays.stream(MenuBoard.values())
                .filter(menuBoard -> menuBoard.name.equals(name))
                .findFirst()
                .orElseThrow(OrderException::new);
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
