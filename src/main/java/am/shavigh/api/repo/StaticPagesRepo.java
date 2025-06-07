package am.shavigh.api.repo;

import am.shavigh.api.model.statics.StaticPage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaticPagesRepo extends JpaRepository<StaticPage, Integer> {

    StaticPage findByUniqueNameAndStatus(String uniqueName, String status);
}
