package christmas.domain;

import christmas.domain.exception.EventDayException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class EventDay {
    private static final int FIRST_DAY = 1;
    private static final int LAST_DAY = 31;
    private static final int CHRISTMAS = 25;
    private static final int YEAR = 2023;
    private final int day;
    private final DayOfWeek dayOfWeek;

    public EventDay(int day, DayOfWeek dayOfWeek) {
        this.day = day;
        this.dayOfWeek = dayOfWeek;
    }

    public static EventDay from(String input) {
        validate(input);
        int inputDay = Integer.parseInt(input);
        return new EventDay(inputDay, getDayOfWeek(inputDay));
    }

    public boolean isBeforeChristmas() {
        return day <= CHRISTMAS;
    }

    public boolean isWeekEnd() {
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public boolean isSpecial() {
        return dayOfWeek == DayOfWeek.SUNDAY || day == CHRISTMAS;
    }

    private static DayOfWeek getDayOfWeek(int day) {
        LocalDate date = LocalDate.of(YEAR, Month.DECEMBER, day);
        return date.getDayOfWeek();
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

    public int getDay() {
        return day;
    }
}
