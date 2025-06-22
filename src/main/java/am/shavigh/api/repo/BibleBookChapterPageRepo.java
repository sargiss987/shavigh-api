package am.shavigh.api.repo;

import am.shavigh.api.model.bibles.BibleBookChapterPages;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BibleBookChapterPageRepo extends JpaRepository<BibleBookChapterPages, Long> {
    List<BibleBookChapterPages> findByStatus(String draft);
}
