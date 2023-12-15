package christmas.domain;

public enum Badge {
    NONE("없음", 0),
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000);

    private final String badge;
    private final int price;

    Badge(String badge, int price) {
        this.badge = badge;
        this.price = price;
    }

    public static Badge getByPrice(int benefitPrice) {
        if (benefitPrice >= SANTA.price) {
            return SANTA;
        }
        if (benefitPrice >= TREE.price) {
            return TREE;
        }
        if (benefitPrice >= STAR.price) {
            return SANTA;
        }
        return NONE;
    }

    public String getBadge() {
        return badge;
    }
}
