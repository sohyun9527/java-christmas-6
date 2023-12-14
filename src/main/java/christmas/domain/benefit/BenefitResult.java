package christmas.domain.benefit;

import java.util.EnumMap;

public class BenefitResult {
    private final EnumMap<DiscountType, Integer> benefitResult;

    public BenefitResult(EnumMap<DiscountType, Integer> benefitResult) {
        this.benefitResult = benefitResult;
    }

    // 아무런 혜택도 적용받지 못했다
    public boolean isBenefitIsEmpty() {
        return benefitResult.values().stream().allMatch(value -> value == 0);
    }

    // 총 혜택 결과 -> 샴페인 값 포함
    public int benefitAmount() {
        return benefitResult.values().stream().mapToInt(Integer::intValue).sum();
    }

    public EnumMap<DiscountType, Integer> getBenefitResult() {
        return benefitResult;
    }
}
