package am.shavigh.api.repo;

import am.shavigh.api.model.inbox.InBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InBoxRepo extends JpaRepository<InBox, Long> {

    @Query(value = """
                SELECT *
                FROM in_box
                ORDER BY local_date DESC
                LIMIT 10000
            """, nativeQuery = true)
    List<InBox> getFirst10ThousandByOrderByLocalDateDesc();
}
