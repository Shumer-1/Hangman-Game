package backend.academy.dictionary;

import java.util.Random;

public class RandomTaker {

    private final Random random = new Random();

    public Category takeRandomCategory() {
        int randomValue = random.nextInt(Category.values().length);
        return Category.getByNumber(randomValue);
    }

    public Difficulty takeRandomDifficulty() {
        int randomValue = random.nextInt(Difficulty.values().length);
        return Difficulty.getByNumber(randomValue);
    }
}
