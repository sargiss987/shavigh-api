package am.shavigh.api.controller;

import am.shavigh.api.dto.articles.ArticleDto;
import am.shavigh.api.service.ArticlesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticlesController {

    private final ArticlesService articlesService;

    public ArticlesController(ArticlesService articlesService) {
        this.articlesService = articlesService;
    }

    @GetMapping("/articles")
    public ResponseEntity<List<ArticleDto>> getArticlesByIds(@RequestParam(value = "ids", required = false) List<Integer> ids) {
        List<ArticleDto> articles = articlesService.getArticleIdIn(ids, "publish");
        return ResponseEntity.ok(articles);
    }
}
