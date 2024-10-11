package backend.academy.console;

import backend.academy.dictionary.Category;
import backend.academy.dictionary.Difficulty;
import backend.academy.dictionary.RandomTaker;
import backend.academy.exceptions.WrongInputValueException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputReader {
    private final Scanner scanner;
    private final RandomTaker randomTaker;

    public InputReader(Scanner scanner, RandomTaker randomTaker) {
        this.scanner = scanner;
        this.randomTaker = randomTaker;
    }

    public Category getCategory() {
        Category category;
        try {
            category = Category.valueOf(scanner.nextLine().toUpperCase());
        } catch (IllegalArgumentException e) {
            category = randomTaker.takeRandomCategory();
        }
        return category;
    }

    public Difficulty getDifficulty() {
        Difficulty difficulty;
        try {
            difficulty = Difficulty.valueOf(scanner.nextLine().toUpperCase());
        } catch (IllegalArgumentException e) {
            difficulty = randomTaker.takeRandomDifficulty();
        }
        return difficulty;
    }

    public String getGameInput() throws WrongInputValueException {
        String inputValue = scanner.nextLine().toLowerCase();
        if ((inputValue.length() == 1 && Pattern.matches("[a-z]", inputValue))
            || (inputValue.equals("give me clue"))) {
            return inputValue;
        }

        throw new WrongInputValueException("Input value must be letter or 'give me clue'");
    }

    public String getUserAnswer() {
        return scanner.nextLine().toLowerCase();
    }
}
