package dictionary.repository;

import dictionary.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface WordRepository extends JpaRepository<Word, Long> {

    @Query("select w from Word w where w.title = :title")
    Word findByTitle(@Param("title") String title);

    @Query("select w from Word w where w.title like %:part%")
    List<Word> findByPart(@Param("part") String part);

}

