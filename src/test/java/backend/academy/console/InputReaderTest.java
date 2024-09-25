package backend.academy.console;

import backend.academy.dictionary.Category;
import backend.academy.dictionary.Difficulty;
import backend.academy.dictionary.RandomTaker;
import backend.academy.exceptions.WrongInputValueException;
import java.util.Scanner;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class InputReaderTest {
    private final static Scanner MOCK_SCANNER = Mockito.mock(Scanner.class);
    private final static RandomTaker MOCK_RANDOM_TAKER = Mockito.mock(RandomTaker.class);

    @ParameterizedTest
    @MethodSource("getArgumentsForGetGameInputTest")
    void getGameInputTest(String inputValue, String expectedString, boolean isWrong) {
        Mockito.when(MOCK_SCANNER.nextLine()).thenReturn(inputValue);
        var inputReader = new InputReader(MOCK_SCANNER, MOCK_RANDOM_TAKER);

        try {
            String actualString = inputReader.getGameInput();

            assertThat(actualString).isEqualTo(expectedString);
        } catch (WrongInputValueException e) {
            assertThat(true).isEqualTo(isWrong);
        }
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForGetDifficultyTest")
    void getDifficultyTest(String inputValue, Difficulty expectedDifficulty) {
        Mockito.when(MOCK_SCANNER.nextLine()).thenReturn(inputValue);
        Mockito.when(MOCK_RANDOM_TAKER.takeRandomDifficulty()).thenReturn(Difficulty.EASY);
        var inputReader = new InputReader(MOCK_SCANNER, MOCK_RANDOM_TAKER);

        var actualDifficulty = inputReader.getDifficulty();

        assertThat(actualDifficulty).isEqualTo(expectedDifficulty);
    }

    @ParameterizedTest
    @MethodSource("getArgumentsForGetCategoryTest")
    void getCategoryTest(String inputValue, Category expectedCategory){
        Mockito.when(MOCK_SCANNER.nextLine()).thenReturn(inputValue);
        Mockito.when(MOCK_RANDOM_TAKER.takeRandomCategory()).thenReturn(Category.ANIMALS);
        var inputReader = new InputReader(MOCK_SCANNER, MOCK_RANDOM_TAKER);

        Category actualCategory = inputReader.getCategory();

        assertThat(actualCategory).isEqualTo(expectedCategory);
    }

    private static Stream<Arguments> getArgumentsForGetCategoryTest(){
        return Stream.of(
            Arguments.of("TOWNS", Category.TOWNS),
            Arguments.of("countries", Category.COUNTRIES),
            Arguments.of("fruts", Category.ANIMALS),
            Arguments.of("FRUITS", Category.FRUITS)
        );
    }

    private static Stream<Arguments> getArgumentsForGetDifficultyTest() {
        return Stream.of(
            Arguments.of("easy", Difficulty.EASY),
            Arguments.of("EASY", Difficulty.EASY),
            Arguments.of("hart", Difficulty.EASY),
            Arguments.of("medium", Difficulty.MEDIUM)
        );
    }

    private static Stream<Arguments> getArgumentsForGetGameInputTest() {
        return Stream.of(
            Arguments.of("A", "a", false),
            Arguments.of("GIVE ME CLUE", "give me clue", false),
            Arguments.of("1", null, true),
            Arguments.of("hello", null, true)
        );
    }
}
