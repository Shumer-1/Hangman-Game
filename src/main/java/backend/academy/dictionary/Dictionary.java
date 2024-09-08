package backend.academy.dictionary;

import backend.academy.exceptions.MissingDataException;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

    public static Kit getRandomWord(Category category, Difficulty difficulty) throws MissingDataException {
        fillOutDictionary(category);

        if (DICTIONARY.get(category).get(difficulty).isEmpty()){
            throw new MissingDataException("File hasn't words with this difficulty");
        }

        var random = new Random();
        int randomValue = random.nextInt(DICTIONARY.get(category).get(difficulty).size());

        return DICTIONARY.get(category).get(difficulty).get(randomValue);
    }

    private static CSVReader openDictionaryFile(String path){
        CSVReader reader;
        try {
            reader = new CSVReaderBuilder(new FileReader(path)).build();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return reader;
    }

    private static void fillOutDictionary(Category category){
        if (ALREADY_PRESENT.contains(category)){
            return;
        }

        String path = "src/main/resources/" + category.toString().toLowerCase() + ".csv";
        try (CSVReader reader = openDictionaryFile(path)) {
            String[] nextLine;
            reader.skip(1);
            Map<Difficulty, List<Kit>> values = new HashMap<>();

            values.put(Difficulty.EASY, new ArrayList<>());
            values.put(Difficulty.MEDIUM, new ArrayList<>());
            values.put(Difficulty.HARD, new ArrayList<>());

            while ((nextLine = reader.readNext()) != null) {
                Difficulty difficulty = Difficulty.HARD;
                if (nextLine[0].length() <= BORDER_EASY){
                    difficulty = Difficulty.EASY;
                }
                else if (nextLine[0].length() <= BORDER_MEDIUM){
                    difficulty = Difficulty.MEDIUM;
                }
                values.get(difficulty).add(new Kit(nextLine[0], nextLine[1]));
            }
            DICTIONARY.put(category, values);
            ALREADY_PRESENT.add(category);

        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }

    }
}




