package am.shavigh.api.repo;

import am.shavigh.api.model.BibleBookChapters;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BiblesBookChapterRepo extends JpaRepository<BibleBookChapters, Long> {
}
