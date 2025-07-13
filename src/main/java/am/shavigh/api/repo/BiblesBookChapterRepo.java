package am.shavigh.api.repo;

import am.shavigh.api.model.bibles.BibleBookChapters;
import am.shavigh.api.model.bibles.BibleBooks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BiblesBookChapterRepo extends JpaRepository<BibleBookChapters, Long> {
    List<BibleBookChapters> findByStatus(String status);

    Optional<BibleBookChapters> findTopByBibleBooksOrderByIdDesc(BibleBooks bibleBook);
}
