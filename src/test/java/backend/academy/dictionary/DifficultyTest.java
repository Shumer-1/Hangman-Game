package backend.academy.dictionary;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class DifficultyTest {
    @ParameterizedTest
    @MethodSource("getArgumentsForGetByNumberTest")
    void getByNumberTest(int number, Difficulty expectedDifficulty) {
        var actualDifficulty = Difficulty.getByNumber(number);

        assertThat(actualDifficulty).isEqualTo(expectedDifficulty);
    }

    private static Stream<Arguments> getArgumentsForGetByNumberTest() {
        return Stream.of(
            Arguments.of(1, Difficulty.MEDIUM),
            Arguments.of(0, Difficulty.EASY),
            Arguments.of(2, Difficulty.HARD)
        );
    }
}
