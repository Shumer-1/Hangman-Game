package backend.academy.console;

import backend.academy.dictionary.Category;
import backend.academy.dictionary.Difficulty;
import backend.academy.dictionary.RandomTaker;
import backend.academy.exceptions.WrongInputValueException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputReader {
    private final Scanner scanner;

    public InputReader(InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }

    public Category getCategory() {
        Category category;
        category = switch (scanner.nextLine().toLowerCase()) {
            case "animals" -> Category.ANIMALS;
            case "towns" -> Category.TOWNS;
            case "countries" -> Category.COUNTRIES;
            case "fruits" -> Category.FRUITS;
            case "names" -> Category.NAMES;
            default -> new RandomTaker().takeRandomCategory();
        };
        return category;
    }

    public Difficulty getDifficulty() {
        Difficulty difficulty;
        difficulty = switch (scanner.nextLine().toLowerCase()) {
            case "easy" -> Difficulty.EASY;
            case "medium" -> Difficulty.MEDIUM;
            case "hard" -> Difficulty.HARD;
            default -> new RandomTaker().takeRandomDifficulty();
        };
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
}
