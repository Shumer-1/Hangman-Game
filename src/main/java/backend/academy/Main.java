package backend.academy;

import backend.academy.console.ConsoleWriter;
import backend.academy.console.InputReader;
import backend.academy.dictionary.CSVDataExtractor;
import backend.academy.dictionary.Dictionary;
import backend.academy.dictionary.RandomTaker;
import backend.academy.exceptions.MissingDataException;
import backend.academy.sessions.GameSession;
import java.util.Random;
import java.util.Scanner;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {
    public static void main(String[] args) throws MissingDataException {
        var consoleWriter = new ConsoleWriter(System.out);
        var inputReader =
            new InputReader(new Scanner(System.in), new RandomTaker(new Random(System.currentTimeMillis())));
        var dictionary = new Dictionary(new CSVDataExtractor());

        var gameSession = new GameSession(consoleWriter, inputReader, dictionary);
        gameSession.startSession();
    }
}
