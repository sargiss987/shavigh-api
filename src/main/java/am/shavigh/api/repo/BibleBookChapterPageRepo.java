package am.shavigh.api.repo;

import am.shavigh.api.model.bibles.BibleBookChapterPages;
import am.shavigh.api.model.bibles.BibleBookChapters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BibleBookChapterPageRepo extends JpaRepository<BibleBookChapterPages, Long> {
    List<BibleBookChapterPages> findByStatus(String draft);

    List<BibleBookChapterPages> findByIdIn(List<Long> unattachedPagesIds);

    List<BibleBookChapterPages> findByBibleBookChaptersIdAndStatus(Long chapterId, String status);

    @Query(value = """
                SELECT *
                FROM bible_book_chapter_pages
                WHERE attached = false
                  AND status = 'publish'
            """, nativeQuery = true)
    List<BibleBookChapterPages> findBibleBookChapterPagesByAttachedFalseAndStatusPublish();


    List<BibleBookChapterPages> findAllByBibleBookChapters(BibleBookChapters bibleBookChapters);

    @Query(value = """
                SELECT *
                FROM bible_book_chapter_pages
                WHERE bible_book_chapter_id = :chapterId
                  AND id NOT IN (:attachedPagesIds)
            """, nativeQuery = true)
    List<BibleBookChapterPages> findByChapterIdAndIdsNotIn(
            @Param("chapterId") Long chapterId,
            @Param("attachedPagesIds") List<Long> attachedPagesIds
    );
}
