package backend.academy.dictionary;

public enum Category {
    TOWNS,
    ANIMALS,
    NAMES,
    COUNTRIES,
    FRUITS;

    private static final Category[] CACHED_VALUES = Category.values();

    public static Category getByNumber(int number) {
        return CACHED_VALUES[number];
    }
}
