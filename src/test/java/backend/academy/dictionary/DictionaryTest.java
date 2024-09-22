package backend.academy.dictionary;

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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DictionaryTest {
    private static final DataExtractor MOCK_DATA_EXTRACTOR = Mockito.mock(CSVDataExtractor.class);
    private static final List<Kit> EXPECTED_KIT_ANIMAL_LIST = List.of(
        new Kit("crocodile", "dangerous reptile"),
        new Kit("lion", "king of beasts"),
        new Kit("tiger", "big cat with stripes"));
    private static final List<Kit> EXPECTED_KIT_COUNTRY_LIST = List.of(
        new Kit("england", "island nation where Newton lived"),
        new Kit("netherlands", "country whose capital is Amsterdam"),
        new Kit("iraq", "country whose capital is Baghdad"));
    private static final List<Kit> EXPECTED_KIT_FRUIT_LIST = List.of(
        new Kit("orange", "bears the name of the color"),
        new Kit("grapefruit", "bitter red-orange citrus"),
        new Kit("plum", "purple small fruit"));

    @SneakyThrows()
    @ParameterizedTest
    @MethodSource("getArgumentsForGetRandomWordTest")
    void getRandomWordTest(Category category, Difficulty difficulty, List<Kit> kits, String expectedWord) {
        when(MOCK_DATA_EXTRACTOR.load(any(String.class))).thenReturn(kits);
        Dictionary dictionary = new Dictionary(MOCK_DATA_EXTRACTOR);

        String actualWord = dictionary.getRandomWord(category, difficulty).word();

        assertThat(actualWord).isEqualTo(expectedWord);
    }

    private static Stream<Arguments> getArgumentsForGetRandomWordTest() {
        return Stream.of(
            Arguments.of(Category.ANIMALS, Difficulty.EASY, EXPECTED_KIT_ANIMAL_LIST, "lion"),
            Arguments.of(Category.COUNTRIES, Difficulty.MEDIUM, EXPECTED_KIT_COUNTRY_LIST, "england"),
            Arguments.of(Category.FRUITS, Difficulty.HARD, EXPECTED_KIT_FRUIT_LIST, "grapefruit")
        );
    }
}
