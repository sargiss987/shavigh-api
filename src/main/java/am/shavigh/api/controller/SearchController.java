package am.shavigh.api.controller;


import am.shavigh.api.dto.chapters.BibleBookChapterDto;
import am.shavigh.api.service.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<BibleBookChapterDto>> searchByWord(@RequestParam("word") String word) {
        List<BibleBookChapterDto> results = searchService.searchByWord(word);
        return ResponseEntity.ok(results);
    }
}
