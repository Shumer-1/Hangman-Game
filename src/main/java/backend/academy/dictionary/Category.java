package backend.academy.dictionary;

import java.util.Arrays;

public enum Category {
    TOWNS(0),
    ANIMALS(1),
    NAMES(2),
    COUNTRIES(3),
    FRUITS(4);

    private final int number;

    Category(int number) {
        this.number = number;
    }

    public static Category getByNumber(int number) {
        return Arrays.stream(values())
            .filter(it -> it.number == number)
            .findFirst().orElseThrow(() -> new IllegalArgumentException("Wrong number of category"));
    }

}
