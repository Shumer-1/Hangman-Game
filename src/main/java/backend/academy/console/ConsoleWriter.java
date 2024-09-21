package backend.academy.console;

import java.io.PrintStream;

public class ConsoleWriter {

    private final PrintStream printer;

    public ConsoleWriter(PrintStream printStream) {
        printer = printStream;
    }

    public void printDifficultyInfo() {
        printer.println("""
            Please enter one of the difficulties: easy, medium, hard.
            If you want to use a random difficulty, press Enter.
            If you enter an incorrect word, the difficulty will also be selected randomly.
            """);
    }

    public void printMessage(String message) {
        printer.println(message);
    }

    public void printCategoryInfo() {
        printer.println("""
            Please enter one of the categories: animals, towns, fruits, countries, names.
            If you want to use a random category, press Enter.
            If you enter an incorrect word, the category will also be selected randomly.
            """);
    }

    public void printClueInfo() {
        printer.println("To get a clue, enter \"give me clue\" at any time");
    }

    public void printRuleInfo() {
        printer.println("""
            The player tries to guess the hidden word by entering\s
            letters one at a time. The word is selected by difficulty level,\s
            randomly from a predefined list of words and category.\s
            The number of attempts is limited, and for each incorrect guess,\s
            part of the gallows and a gallows figurine are visualized.
            """);
    }
}
