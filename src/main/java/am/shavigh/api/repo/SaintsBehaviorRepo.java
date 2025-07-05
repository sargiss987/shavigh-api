package am.shavigh.api.repo;

import am.shavigh.api.dto.saintsbehaviour.SaintsBehaviorFlatDto;
import am.shavigh.api.dto.saintsbehaviour.SaintsBehaviourSectionFullDto;
import am.shavigh.api.model.saintsbehavior.SaintsBehavior;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
            WHERE sbs.status = :status
            ORDER BY sb.id, sbs.id
            """, nativeQuery = true)
    List<SaintsBehaviorFlatDto> getSaintsBehaviorWithSections(String status);


    @Query(value = """
            SELECT new am.shavigh.api.dto.saintsbehaviour.SaintsBehaviourSectionFullDto(
                sb.id,
                sb.title,
                sb.content,
                sb.url,
                sb.status,
                sb.originId,
                sb.saintsBehavior.id
            )
            FROM SaintsBehaviorSection sb
            WHERE sb.url = :url AND sb.status = :status
            """)
    SaintsBehaviourSectionFullDto findByUrl(String url, String status);
}
