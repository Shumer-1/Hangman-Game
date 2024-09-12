package backend.academy.dictionary;

import java.util.List;

public interface DataExtractor {
    List<Kit> load(String path);
}
