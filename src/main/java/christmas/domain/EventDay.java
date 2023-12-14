package christmas.domain;

import christmas.domain.exception.DayException;

public class EventDay {

    private static final int START_DAY = 1;
    private static final int LAST_DAY = 31;
    private final int day;

    public EventDay(int day) {
        validateDayRange(day);
        this.day = day;
    }

    public static EventDay from(String input) {
        try {
            int day = Integer.parseInt(input);
            return new EventDay(day);
        } catch (NumberFormatException e) {
            throw new DayException();
        }
    }

    // LocalDate로 리팩하기
    private void validateDayRange(int day) {
        if (day < START_DAY || day > LAST_DAY) {
            throw new DayException();
        }
    }

    public int getDay() {
        return day;
    }
}
