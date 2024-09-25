package backend.academy.dictionary;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class CategoryTest {

    @ParameterizedTest
    @MethodSource("getArgumentsForGetByNumberTest")
    void getByNumberTest(int number, Category expectedCategory) {
        var actualCategory = Category.getByNumber(number);

        assertThat(actualCategory).isEqualTo(expectedCategory);
    }

    private static Stream<Arguments> getArgumentsForGetByNumberTest() {
        return Stream.of(
            Arguments.of(1, Category.ANIMALS),
            Arguments.of(0, Category.TOWNS),
            Arguments.of(4, Category.FRUITS)
        );
    }

}
