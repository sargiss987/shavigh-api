package am.shavigh.api.repo;

import am.shavigh.api.model.articles.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticlesRepo extends JpaRepository<Article, Integer> {

    List<Article> findByIdInAndStatus(List<Integer> ids, String status);
}
