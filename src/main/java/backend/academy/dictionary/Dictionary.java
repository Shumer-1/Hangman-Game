package backend.academy.dictionary;

import backend.academy.exceptions.MissingDataException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Dictionary {
    private final static Map<Category, Map<Difficulty, List<Kit>>> DICTIONARY = new HashMap<>();
    private final static int BORDER_EASY = 4;
    private final static int BORDER_MEDIUM = 8;
    private final static List<Category> ALREADY_PRESENT = new ArrayList<>();
    private final DataExtractor dataExtractor;

    public Dictionary(DataExtractor dataExtractor) {
        this.dataExtractor = dataExtractor;

    }

    public Kit getRandomWord(Category category, Difficulty difficulty) throws MissingDataException {
        fillOutDictionary(category);

        if (DICTIONARY.get(category).get(difficulty).isEmpty()) {
            throw new MissingDataException("File hasn't words with this difficulty");
        }

        var random = new Random(System.currentTimeMillis());
        int randomValue = random.nextInt(DICTIONARY.get(category).get(difficulty).size());

        return DICTIONARY.get(category).get(difficulty).get(randomValue);
    }

    private void fillOutDictionary(Category category) {
        if (ALREADY_PRESENT.contains(category)) {
            return;
        }

        String path = "src/main/resources/" + category.toString().toLowerCase() + ".csv";
        List<Kit> kits = dataExtractor.load(path);

        Map<Difficulty, List<Kit>> values = new HashMap<>();
        values.put(Difficulty.EASY, kits.stream().filter(kit -> kit.word().length() <= BORDER_EASY).toList());
        values.put(Difficulty.MEDIUM,
            kits.stream().filter(kit -> kit.word().length() <= BORDER_MEDIUM && kit.word().length() > BORDER_EASY)
                .toList());
        values.put(Difficulty.HARD, kits.stream().filter(kit -> kit.word().length() > BORDER_MEDIUM).toList());

        DICTIONARY.put(category, values);
        ALREADY_PRESENT.add(category);
    }
}




