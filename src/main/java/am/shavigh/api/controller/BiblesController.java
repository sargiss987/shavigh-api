package am.shavigh.api.controller;

import am.shavigh.api.dto.bibles.BibleDto;
import am.shavigh.api.dto.chapters.BibleBookChapterDto;
import am.shavigh.api.dto.chapters.BiblesChapterPublishDto;
import am.shavigh.api.dto.chapters.CreateBibleBookChapterDto;
import am.shavigh.api.dto.pages.BibleBookChapterPageDto;
import am.shavigh.api.dto.pages.BibleBookChapterPageMinDataDto;
import am.shavigh.api.dto.pages.CreateBibleBookChapterPageDto;
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
    public ResponseEntity<BibleBookChapterDto> createOrUpdateBiblesChapter(@RequestBody CreateBibleBookChapterDto createBibleBookChapterDto) {
        return ResponseEntity.ok(biblesService.createBiblesChapter(createBibleBookChapterDto));
    }

    @PutMapping("/bibles/chapters/publish")
    public ResponseEntity<Void> publishBiblesChapter(@RequestBody BiblesChapterPublishDto biblesChapterPublishDto) {
        biblesService.publishBiblesChapter(biblesChapterPublishDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/bibles/chapters")
    public ResponseEntity<BibleBookChapterDto> getBiblesChapterByUrl(@RequestParam("url") String url,
                                                                     @RequestParam(value = "status", defaultValue = "publish") String status) {
        return ResponseEntity.ok(biblesService.getBiblesChapterByUrl(url, status));
    }

    @GetMapping("/bibles/chapters/draft")
    public ResponseEntity<List<BibleBookChapterDto>> getDraftBiblesChapters() {
        return ResponseEntity.ok(biblesService.findByStatusIsDraft("draft"));
    }

    @GetMapping("/bibles/chapters/pages")
    public ResponseEntity<BibleBookChapterPageDto> getBiblesChapterPagesByUrl(@RequestParam("url") String url,
                                                                              @RequestParam(value = "status", defaultValue = "publish") String status) {
        return ResponseEntity.ok(biblesService.getBiblesChapterPagesByUrl(url, status));
    }

    @GetMapping("/bibles/chapters/pages/{chapterId}")
    public ResponseEntity<List<BibleBookChapterPageMinDataDto>> getBiblesChapterPagesByChapterId(@PathVariable("chapterId") Long chapterId,
                                                                                                 @RequestParam(value = "status", defaultValue = "publish") String status) {
        return ResponseEntity.ok(biblesService.getBiblesChapterPagesByChapterId(chapterId, status));
    }

    @PostMapping("/bibles/chapters/pages")
    public ResponseEntity<BibleBookChapterPageDto> createOrUpdateBiblesChapterPage(@RequestBody CreateBibleBookChapterPageDto createBibleBookChapterPageDto) {
        return ResponseEntity.ok(biblesService.createOrUpdateBiblesChapterPage(createBibleBookChapterPageDto));
    }

    @PutMapping("/bibles/chapters/pages/publish")
    public ResponseEntity<Void> publishBiblesChapterPage(@RequestBody BiblesChapterPublishDto biblesChapterPublishDto) {
        biblesService.publishBiblesChapterPage(biblesChapterPublishDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/bibles/chapters/pages/draft")
    public ResponseEntity<List<BibleBookChapterPageDto>> getDraftBiblesChapterPages() {
        return ResponseEntity.ok(biblesService.findDraftBiblesChapterPages("draft"));
    }
}
