package backend.academy.sessions;

import backend.academy.console.ConsoleWriter;
import backend.academy.console.InputReader;
import backend.academy.dictionary.Category;
import backend.academy.dictionary.Dictionary;
import backend.academy.dictionary.Difficulty;
import backend.academy.dictionary.Kit;
import backend.academy.exceptions.MissingDataException;
import backend.academy.exceptions.WrongInputValueException;
import backend.academy.gameLogic.GameLogicUtil;
import backend.academy.renderer.EasyModeHangmanRenderer;
import backend.academy.renderer.HangmanRenderer;
import backend.academy.renderer.HardModeHangmanRenderer;
import backend.academy.renderer.MediumModeHangmanRenderer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;

public class GameSession {

    private final ConsoleWriter consoleWriter;
    private final InputReader inputReader;
    private final Dictionary dictionary;
    private Category wordCategory;
    private Difficulty gameDifficulty;
    private HangmanRenderer renderer;
    private String word;
    private final int mistakesNumberForHard = 8;
    private final int mistakesNumberForMedium = 6;
    private final int mistakesNumberForEasy = 3;
    @Getter private int currentMistakes;

    private StringBuilder guessedWord;

    public GameSession(ConsoleWriter consoleWriter, InputReader inputReader, Dictionary dictionary) {
        this.consoleWriter = consoleWriter;
        this.inputReader = inputReader;
        this.dictionary = dictionary;
    }

    @SuppressWarnings("MultipleStringLiterals")
    public void startSession() {
        consoleWriter.printRuleInfo();

        consoleWriter.printCategoryInfo();
        wordCategory = inputReader.getCategory();
        consoleWriter.printMessage("Category is " + wordCategory.toString().toLowerCase() + ".\n");

        consoleWriter.printDifficultyInfo();
        gameDifficulty = inputReader.getDifficulty();
        consoleWriter.printMessage("Difficulty is " + gameDifficulty.toString().toLowerCase() + ".\n");

        renderer = switch (gameDifficulty) {
            case HARD -> new HardModeHangmanRenderer(System.out);
            case MEDIUM -> new MediumModeHangmanRenderer(System.out);
            default -> new EasyModeHangmanRenderer(System.out);
        };

        Kit kit;
        try {
            kit = dictionary.getRandomWord(wordCategory, gameDifficulty);
        } catch (MissingDataException exception) {
            consoleWriter.printMessage("Dictionary problem detected, session ends");
            return;
        }
        word = kit.word();
        playGame(kit);
    }

    @SuppressWarnings("MultipleStringLiterals")
    private void playGame(Kit kit) {
        guessedWord = new StringBuilder(Stream.generate(() -> "_")
            .limit(kit.word().length())
            .collect(Collectors.joining()));

        consoleWriter.printStartGameInfo();
        consoleWriter.printClueInfo();
        consoleWriter.printWordCurrentState(guessedWord);

        currentMistakes = 0;
        int maxMistakesNumber = switch (gameDifficulty) {
            case HARD -> mistakesNumberForHard;
            case MEDIUM -> mistakesNumberForMedium;
            default -> mistakesNumberForEasy;
        };

        consoleWriter.printMessage("You have " + maxMistakesNumber);

        while (currentMistakes < maxMistakesNumber) {
            try {
                var userInput = inputReader.getGameInput();
                if (userInput.length() == 1) {
                    boolean result = GameLogicUtil.gameStep(userInput, kit.word(), guessedWord);
                    if (!result) {
                        currentMistakes++;
                        renderer.render(currentMistakes);
                    }
                } else if (userInput.equals("give me clue")) {
                    consoleWriter.printMessage(kit.clue() + ".\n");
                }
                consoleWriter.printMessage("You have " + (maxMistakesNumber - currentMistakes) + " tries");
                consoleWriter.printWordCurrentState(guessedWord);
                if (guessedWord.indexOf("_") == -1) {
                    break;
                }
            } catch (WrongInputValueException e) {
                consoleWriter.printMessage("Please enter a letter");
            }
        }

        endGame(currentMistakes != maxMistakesNumber);

    }

    @SuppressWarnings("MultipleStringLiterals")
    private void endGame(boolean isWin) {
        if (isWin) {
            consoleWriter.printMessage("You win!!!");
        } else {
            consoleWriter.printMessage(word);
            consoleWriter.printMessage("You lose!!!");
        }
        consoleWriter.printMessage("Shall you play again? Yes/No");
        String isAgain = inputReader.getUserAnswer();
        while (!isAgain.equals("yes") && !isAgain.equals("no")) {
            consoleWriter.printMessage("Please write 'Yes' or 'No'");
            isAgain = inputReader.getUserAnswer();
        }
        if (isAgain.equals("yes")) {
            startSession();
        }
    }
}
