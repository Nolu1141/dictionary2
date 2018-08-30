package dictionary.service;
import dictionary.model.Word;

import java.util.List;

public interface WordServise {

    Word getById(Long id);
    void save(Word word);
    void delete(Long id);
    Word findByTitle(String word);
    List<Word> getAll();
    List<Word> findByPart(String part);
}
