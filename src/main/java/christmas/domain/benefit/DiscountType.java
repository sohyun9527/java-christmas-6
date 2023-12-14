package christmas.domain.benefit;

public enum DiscountType {
    CHRISTMAS("크리스마스 디데이 할인"),
    WEEKEND("주말 할인"),
    WEEKDAY("평일 할인"),
    SPECIAL("특별 할인"),
    PROMOTION("증정 메뉴");

    private final String type;

    DiscountType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
