package am.shavigh.api.controller;

import am.shavigh.api.dto.bibles.BibleDto;
import am.shavigh.api.dto.chapters.BibleBookChapterDto;
import am.shavigh.api.dto.chapters.CreateBibleBookChapterDto;
import am.shavigh.api.dto.pages.BibleBookChapterPageDto;
import am.shavigh.api.service.BiblesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BiblesController {

    private final BiblesService biblesService;

    public BiblesController(BiblesService biblesService) {
        this.biblesService = biblesService;
    }


    @GetMapping("/bibles")
    public ResponseEntity<List<BibleDto>> getBibles() {
        return ResponseEntity.ok(biblesService.getBibleDtoList());
    }

    @PostMapping("/bibles/chapters")
    public ResponseEntity<BibleBookChapterDto> createBiblesChapter(@RequestBody CreateBibleBookChapterDto createBibleBookChapterDto) {
        return ResponseEntity.ok(biblesService.createBiblesChapter(createBibleBookChapterDto));
    }

    @GetMapping("/bibles/chapters")
    public ResponseEntity<BibleBookChapterDto> getBiblesChapterByUrl(@RequestParam("url") String url) {
        return ResponseEntity.ok(biblesService.getBiblesChapterByUrl(url));
    }

    @GetMapping("/bibles/chapters/pages")
    public ResponseEntity<BibleBookChapterPageDto> getBiblesChapterPagesByUrl(@RequestParam("url") String url) {
        return ResponseEntity.ok(biblesService.getBiblesChapterPagesByUrl(url));
    }
}
