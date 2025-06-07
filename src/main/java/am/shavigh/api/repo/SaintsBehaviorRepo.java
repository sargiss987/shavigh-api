package am.shavigh.api.repo;

import am.shavigh.api.dto.saintsbehaviour.SaintsBehaviorFlatDto;
import am.shavigh.api.model.saintsbehavior.SaintsBehavior;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaintsBehaviorRepo extends JpaRepository<SaintsBehavior, Long> {

    @Query(value = """
        SELECT 
            sb.id AS id,
            sb.title AS title,
            sb.url AS url,
            sb.status AS status,
            sbs.id AS sectionId,
            sbs.title AS sectionTitle,
            sbs.content AS sectionContent,
            sbs.url AS sectionUrl,
            sbs.status AS sectionStatus
        FROM saints_behavior sb
        INNER JOIN saints_behavior_section sbs ON sbs.saints_behavior_id = sb.id
        """, nativeQuery = true)
    List<SaintsBehaviorFlatDto> getSaintsBehaviorWithSections();
}
