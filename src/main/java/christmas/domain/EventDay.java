package christmas.domain;

import christmas.domain.exception.DayException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class EventDay {

    private static final int YEAR = 2023;
    private static final int START_DAY = 1;
    private static final int LAST_DAY = 31;
    private static final int CHRISTMAS = 25;
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

    public boolean isWeekEnd() {
        return getDayOfWeek() == DayOfWeek.FRIDAY || getDayOfWeek() == DayOfWeek.SATURDAY;
    }

    public boolean isSpecial() {
        return day == CHRISTMAS || getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public boolean isBeforeChristmas() {
        return day <= CHRISTMAS;
    }

    public DayOfWeek getDayOfWeek() {
        LocalDate date = LocalDate.of(YEAR, Month.DECEMBER, day);

        return date.getDayOfWeek();
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
