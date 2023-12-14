package christmas.domain;

import java.util.Arrays;

public enum Condition {
    RESTART("R", 1),
    QUIT("Q", 0);

    private final String command;
    private final int number;

    Condition(String command, int number) {
        this.command = command;
        this.number = number;
    }

    public static Condition from(String command) {
        return Arrays.stream(values())
                .filter(condition -> condition.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 재시작은 R, 종료하기는 Q 입니다!"));
    }
}
