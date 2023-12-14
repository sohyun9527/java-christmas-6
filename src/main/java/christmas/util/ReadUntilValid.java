package christmas.util;

import java.util.function.Supplier;

public class ReadUntilValid {
    public static <T> T readUntilValid(Supplier<T> inputFunction) {
        while (true) {
            try {
                return inputFunction.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
