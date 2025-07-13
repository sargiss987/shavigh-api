package am.shavigh.api.repo;

import am.shavigh.api.model.bibles.BibleBookChapterPages;
import am.shavigh.api.model.bibles.BibleBookChapters;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BibleBookChapterPageRepo extends JpaRepository<BibleBookChapterPages, Long> {
    List<BibleBookChapterPages> findByStatus(String draft);

    List<BibleBookChapterPages> findByIdIn(List<Long> unattachedPagesIds);

    List<BibleBookChapterPages> findByIdNotIn(List<Long> unattachedPagesIds);

    List<BibleBookChapterPages> findByBibleBookChaptersIdAndStatus(Long chapterId, String status);

    List<BibleBookChapterPages> findBibleBookChapterPagesByAttachedFalse();

    List<BibleBookChapterPages> findAllByBibleBookChapters(BibleBookChapters bibleBookChapters);

}
