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
    @Getter private int currentMistakes;
    private static String stringEnd = ".\n";
    private static String messageString = "You have ";
    private static String consentString = "yes";
    private static String failureString = "no";

    private StringBuilder guessedWord;

    public GameSession(ConsoleWriter consoleWriter, InputReader inputReader, Dictionary dictionary) {
        this.consoleWriter = consoleWriter;
        this.inputReader = inputReader;
        this.dictionary = dictionary;
    }

    public void startSession() {
        consoleWriter.printRuleInfo();

        consoleWriter.printCategoryInfo();
        wordCategory = inputReader.getCategory();

        consoleWriter.printMessage("Category is " + wordCategory.toString().toLowerCase() + stringEnd);

        consoleWriter.printDifficultyInfo();
        gameDifficulty = inputReader.getDifficulty();
        consoleWriter.printMessage("Difficulty is " + gameDifficulty.toString().toLowerCase() + stringEnd);

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

    private void playGame(Kit kit) {
        guessedWord = new StringBuilder(Stream.generate(() -> "_")
            .limit(kit.word().length())
            .collect(Collectors.joining()));

        consoleWriter.printStartGameInfo();
        consoleWriter.printClueInfo();
        consoleWriter.printWordCurrentState(guessedWord);

        currentMistakes = 0;
        int maxMistakesNumber = gameDifficulty.mistakesNumber;
        consoleWriter.printMessage(messageString + maxMistakesNumber);

        while (currentMistakes < maxMistakesNumber) {
            try {
                var userInput = inputReader.getGameInput();
                if (userInput.length() == 1) {
                    boolean result = GameLogicUtil.guessLetter(userInput, kit.word(), guessedWord);
                    if (!result) {
                        currentMistakes++;
                        renderer.render(currentMistakes);
                    }
                } else if (userInput.equals("give me clue")) {
                    consoleWriter.printMessage(kit.clue() + stringEnd);
                }
                consoleWriter.printMessage(messageString + (maxMistakesNumber - currentMistakes) + " tries");
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

    private void endGame(boolean isWin) {
        if (isWin) {
            consoleWriter.printMessage("You win!!!");
        } else {
            consoleWriter.printMessage(word);
            consoleWriter.printMessage("You lose!!!");
        }
        consoleWriter.printMessage("Shall you play again? Yes/No");
        String isAgain = inputReader.getUserAnswer();
        while (!isAgain.equals(consentString) && !isAgain.equals(failureString)) {
            consoleWriter.printMessage("Please write 'Yes' or 'No'");
            isAgain = inputReader.getUserAnswer();
        }
        if (isAgain.equals(consentString)) {
            startSession();
        }
    }
}
