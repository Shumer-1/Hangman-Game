package backend.academy.sessions;

import backend.academy.console.ConsoleWriter;
import backend.academy.console.InputReader;
import backend.academy.dictionary.Category;
import backend.academy.dictionary.Dictionary;
import backend.academy.dictionary.Difficulty;
import backend.academy.dictionary.Kit;
import java.util.List;
import java.util.stream.Stream;
import lombok.SneakyThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class GameSessionTest {
    private final ConsoleWriter MOCK_CONSOLE_WRITER = Mockito.mock(ConsoleWriter.class);
    private final InputReader MOCK_INPUT_READER = Mockito.mock(InputReader.class);
    private final Dictionary MOCK_DICTIONARY = Mockito.mock(Dictionary.class);

    @SneakyThrows @ParameterizedTest
    @MethodSource("getArgumentsForStartSessionTest")
    void startSessionTest(List<String> answers, Kit kit, int expectedMistakes){
        Mockito.when(MOCK_INPUT_READER.getCategory()).thenReturn(Category.ANIMALS);
        Mockito.when(MOCK_INPUT_READER.getDifficulty()).thenReturn(Difficulty.EASY);
        Mockito.when(MOCK_INPUT_READER.getUserAnswer()).thenReturn("no");
        var mockSequence = Mockito.when(MOCK_INPUT_READER.getGameInput()).thenReturn(answers.getFirst());
        for (int i = 1; i < answers.size(); i++) {
            mockSequence.thenReturn(answers.get(i));
        }
        Mockito.when(MOCK_DICTIONARY.getRandomWord(Category.ANIMALS, Difficulty.EASY)).thenReturn(kit);
        var gameSession = new GameSession(MOCK_CONSOLE_WRITER, MOCK_INPUT_READER, MOCK_DICTIONARY);

        gameSession.startSession();
        int actualMistakes = gameSession.currentMistakes();

        assertThat(actualMistakes).isEqualTo(expectedMistakes);
    }

    private static Stream<Arguments> getArgumentsForStartSessionTest(){
        return Stream.of(
            Arguments.of(List.of("a", "b", "c", "d"), new Kit("abcd", "a"), 0),
            Arguments.of(List.of("l", "e", "h", "c", "o"), new Kit("hello", "a"), 1),
            Arguments.of(List.of("a", "b", "c"), new Kit("dogs", "a"), 3)
        );
    }



}
