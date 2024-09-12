package backend.academy.dictionary;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVDataExtractor implements DataExtractor {

    private static CSVReader openDictionaryFile(String path) {
        CSVReader reader;
        try {
            reader = new CSVReaderBuilder(new FileReader(path)).build();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return reader;
    }

    @Override
    public List<Kit> load(String path) {
        try (CSVReader reader = openDictionaryFile(path)) {
            String[] nextLine;
            reader.skip(1);
            List<Kit> result = new ArrayList<>();

            while ((nextLine = reader.readNext()) != null) {
                result.add(new Kit(nextLine[0], nextLine[1]));
            }
            return result;
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}
