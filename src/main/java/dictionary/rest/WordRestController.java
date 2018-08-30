package dictionary.rest;

import dictionary.model.Word;
import dictionary.service.WordServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/dictionary")
public class WordRestController {

    @Autowired
    private WordServise wordServise;

    @RequestMapping(value = "id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Word> getWord(@PathVariable("id") Long id){
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Word word = this.wordServise.getById(id);

        if (word == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println(word.toString());
        return new ResponseEntity<>(word, HttpStatus.OK);
    }

    @RequestMapping(value = "title/{wordToFind}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Word> getWord(@PathVariable("wordToFind") String wordToFind){

        System.out.println(wordToFind);

        Word word = this.wordServise.findByTitle(wordToFind);

        if (word == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println(word.toString());
        return new ResponseEntity<>(word, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Word> saveWord(@RequestBody Word word) {

        if (word == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        System.out.println(word.toString());
        this.wordServise.save(word);
        return new ResponseEntity<>(word, HttpStatus.CREATED);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Word> updateWord(@RequestBody Word newWord) {

        Word word = wordServise.findByTitle(newWord.getTitle());

        if (newWord == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        word.setDescription(newWord.getDescription());
        this.wordServise.save(word);

        return new ResponseEntity<>(word, HttpStatus.OK);
    }

    @RequestMapping(value = "{wordForDel}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Word> deleteWord(@PathVariable("wordForDel") String wordForDel) {
        Word word = this.wordServise.findByTitle(wordForDel);

        if (word == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.wordServise.delete(word.getId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Word>> getAllWords() {
        List<Word> words = this.wordServise.getAll();

        if (words.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(words, HttpStatus.OK);
    }

    @RequestMapping(value = "p/{part}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Word>> getWordsByPart(@PathVariable("part") String part) {
        List<Word> words = this.wordServise.findByPart(part);

        if (words.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(words, HttpStatus.OK);
    }

}
