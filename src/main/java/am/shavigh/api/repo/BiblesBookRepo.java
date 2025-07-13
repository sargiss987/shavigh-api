package am.shavigh.api.repo;

import am.shavigh.api.model.bibles.BibleBooks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BiblesBookRepo extends JpaRepository<BibleBooks, Long> {}
