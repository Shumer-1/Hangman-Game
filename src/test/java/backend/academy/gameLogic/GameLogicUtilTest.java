package backend.academy.gameLogic;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class GameLogicUtilTest {

    @ParameterizedTest
    @MethodSource("getArgumentsForGuessLetterTest")
    void guessLetterTest(String letter, String gameWord, StringBuilder guessedWord, boolean expectedResult, String expectedWord){
        boolean actualResult = GameLogicUtil.guessLetter(letter, gameWord, guessedWord);

        assertAll(
            () -> assertThat(actualResult).isEqualTo(expectedResult),
            () -> assertThat(guessedWord.toString()).isEqualTo(expectedWord)
        );
    }

    private static Stream<Arguments> getArgumentsForGuessLetterTest(){
        return Stream.of(
            Arguments.of("a", "arguments", new StringBuilder("_________"), true, "a________"),
            Arguments.of("b", "arguments", new StringBuilder("_________"), false, "_________"),
            Arguments.of("a", "aaaa", new StringBuilder("____"), true, "aaaa")
        );
    }

}
