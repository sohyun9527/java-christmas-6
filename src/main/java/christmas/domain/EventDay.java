package christmas.domain;

import christmas.domain.exception.EventDayException;

public class EventDay {
    private static final int FIRST_DAY = 1;
    private static final int LAST_DAY = 31;
    private final int day;

    public EventDay(int day) {
        this.day = day;
    }

    // 숫자가 아닌가?
    // 2023.12 내의 수인가?
    public static EventDay from(String input) {
        validate(input);
        return new EventDay(Integer.parseInt(input));
    }

    private static void validate(String input) {
        validateOnlyDigit(input);
        validateRange(input);
    }

    private static void validateOnlyDigit(String input) {
        if (!input.chars().allMatch(Character::isDigit)) {
            throw new EventDayException();
        }
    }

    private static void validateRange(String input) {
        int day = Integer.parseInt(input);
        if (day < FIRST_DAY || day > LAST_DAY) {
            throw new EventDayException();
        }
    }
}
