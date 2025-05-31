package am.shavigh.api.repo;

import am.shavigh.api.model.BibleBooks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BiblesBookRepo extends JpaRepository<BibleBooks, Integer> {}
