package am.shavigh.api.repo;

import am.shavigh.api.dto.bibles.BibleFlatDto;
import am.shavigh.api.dto.chapters.BibleBookChapterDto;
import am.shavigh.api.model.Bibles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BiblesRepo extends JpaRepository<Bibles, Long> {

    @Query(value = """
            SELECT b.name, b.unique_name, bb.title, bb.serial_number, bt.name, bc.title, bc.url  FROM bibles b
            INNER JOIN bible_books bb on bb.bible_id=b.id
            INNER JOIN bible_translations bt on bb.translation_id=bt.id
            INNER JOIN bible_book_chapters bc on bc.bible_book_id=bb.id
            WHERE bt.name <> 'ru'
            """, nativeQuery = true)
    List<BibleFlatDto> getBibleDtoList();

    @Query("""
                SELECT new am.shavigh.api.dto.chapters.BibleBookChapterDto(
                    bc.title,
                    bc.content,
                    bc.url,
                    bc.linkToDefaultContent,
                    bc.nextLink,
                    bc.prevLink
                )
                FROM BibleBookChapters bc
                WHERE bc.url = :url
            """)
    BibleBookChapterDto findByUrl(@Param("url") String url);
}
