package backend.academy.dictionary;

import java.util.Arrays;

public enum Difficulty {
    EASY(0),
    MEDIUM(1),
    HARD(2);

    private final int number;

    Difficulty(int number) {
        this.number = number;
    }

    public static Difficulty getByNumber(int number) {
        return Arrays.stream(values())
            .filter(it -> it.number == number)
            .findFirst().orElseThrow(() -> new IllegalArgumentException("Wrong number of difficulty"));
    }
}
