package backend.academy.dictionary;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class CSVDataExtractorTest {

    private static final List<Kit> EXPECTED_KIT_TEST1 = List.of(
        new Kit("antelope", "medium-sized animal with horns"),
        new Kit("crocodile", "dangerous reptile"),
        new Kit("elephant", "huge animal with trunk"),
        new Kit("lion", "king of beasts")
    );
    private static final List<Kit> EXPECTED_KIT_TEST2 = List.of(
        new Kit("russia", "largest country in the world"),
        new Kit("mexico", "country with cacti and tequila"),
        new Kit("england", "island nation where Newton lived"),
        new Kit("netherlands", "country whose capital is Amsterdam"),
        new Kit("greece", "birthplace of Pythagoras")
    );
    private static final List<Kit> EXPECTED_KIT_TEST3 = List.of(
        new Kit("apple", "green fruit grows on trees"),
        new Kit("orange", "bears the name of the color"),
        new Kit("grapefruit", "bitter red-orange citrus"),
        new Kit("plum", "purple small fruit"),
        new Kit("peach", "soft fluffy fruit")
    );

    @ParameterizedTest
    @MethodSource("getArgumentsForLoadTest")
    void loadTest(String path, List<Kit> expectedResult) {
        var csvDataExtractor = new CSVDataExtractor();

        List<Kit> actualResult = csvDataExtractor.load(path);

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> getArgumentsForLoadTest() {
        return Stream.of(
            Arguments.of("src/test/resources/dataExtractorTest1.csv", EXPECTED_KIT_TEST1),
            Arguments.of("src/test/resources/dataExtractorTest2.csv", EXPECTED_KIT_TEST2),
            Arguments.of("src/test/resources/dataExtractorTest3.csv", EXPECTED_KIT_TEST3)
        );
    }

}
