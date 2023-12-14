package christmas.domain;

public enum Badge {
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NONE("없음", 0);

    private final String badge;
    private final int amount;

    Badge(String badge, int amount) {
        this.badge = badge;
        this.amount = amount;
    }

    public String getBadge() {
        return badge;
    }

    public static Badge getByAmount(int totalBenefitAmount) {
        if (totalBenefitAmount >= SANTA.amount) {
            return SANTA;
        }
        if (totalBenefitAmount >= TREE.amount) {
            return TREE;
        }
        if (totalBenefitAmount >= STAR.amount) {
            return STAR;
        }
        return NONE;
    }
}

