package am.shavigh.api.service;

import am.shavigh.api.dto.articles.ArticleDto;
import am.shavigh.api.repo.ArticlesRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticlesService {

    private final ArticlesRepo articlesRepo;

    public ArticlesService(ArticlesRepo articlesRepo) {
        this.articlesRepo = articlesRepo;
    }

    public List<ArticleDto> getArticleIdIn(List<Integer> ids, String status) {
        return articlesRepo.findByIdInAndStatus(ids, status).stream().map(article -> {
            ArticleDto articleDto = new ArticleDto();
            articleDto.setId(article.getId());
            articleDto.setTitle(article.getTitle());
            articleDto.setStatus(article.getStatus());
            articleDto.setDescription(article.getDescription());
            articleDto.setUrl(article.getUrl());
            return articleDto;
        }).toList();
    }
}
