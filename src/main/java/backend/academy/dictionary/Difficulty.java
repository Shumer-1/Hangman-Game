package backend.academy.dictionary;

public enum Difficulty {
    EASY(3),
    MEDIUM(6),
    HARD(8);

    public final int mistakesNumber;
    private static final Difficulty[] CACHED_VALUES = Difficulty.values();

    Difficulty(int mistakesNumber) {
        this.mistakesNumber = mistakesNumber;
    }

    public static Difficulty getByNumber(int number) {
        return CACHED_VALUES[number];
    }
}
