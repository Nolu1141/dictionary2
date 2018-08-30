package dictionary.service;

import dictionary.model.Word;
import dictionary.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Service
public class WordServiceImpl implements WordServise {

    @Autowired
    WordRepository wordRepository;

    @Override
    public Word getById(Long id) {
        return wordRepository.findOne(id);
    }

    @Override
    public void save(Word word) {
        wordRepository.save(word);
    }

    @Override
    public void delete(Long id) {
        wordRepository.delete(id);
    }


    @Override
    public Word findByTitle(String word) {
        return wordRepository.findByTitle(word);
    }

    @Override
    public List<Word> getAll() {
        return wordRepository.findAll();
    }

    @Override
    public List<Word> findByPart(String part) {
        return wordRepository.findByPart(part);
    }


}
