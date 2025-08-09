package am.shavigh.api.repo;

import am.shavigh.api.dto.saintsbehaviour.SaintsBehaviorSectionPageDto;
import am.shavigh.api.model.saintsbehavior.SaintsBehaviorSectionPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SaintsBehaviorSectionPageRepo extends JpaRepository<SaintsBehaviorSectionPage, Long> {


    @Query(value = """
            SELECT new am.shavigh.api.dto.saintsbehaviour.SaintsBehaviorSectionPageDto(
                sbp.id,
                sbp.title,
                sbp.content,
                sbp.url,
                sbp.status,
                sbp.originId,
                sbp.saintsBehaviorSection.id,
                sbp.attached
            )
            FROM SaintsBehaviorSectionPage sbp
            WHERE sbp.url = :url AND sbp.status = :status
            """)
    SaintsBehaviorSectionPageDto findByUrl(String url, String status);

    List<SaintsBehaviorSectionPage> findByIdIn(List<Long> unattachedPagesIds);

    List<SaintsBehaviorSectionPage> findByIdNotIn(List<Long> unattachedPagesIds);

    List<SaintsBehaviorSectionPage> findBySaintsBehaviorSectionIdAndStatus(Long sectionId, String status);

    List<SaintsBehaviorSectionPage> findByAttachedFalse();

    List<SaintsBehaviorSectionPage> findAllBySaintsBehaviorSectionId(Long id);

    @Query(value = """
                SELECT *
                FROM saints_behavior_section_page
                WHERE saints_behavior_section_id = :originId
                  AND id NOT IN (:attachedPageIds)
            """, nativeQuery = true)
    List<SaintsBehaviorSectionPage> findBySectionIdAndIdsNotIn(
            @Param("originId") Long originId,
            @Param("attachedPageIds") List<Long> attachedPageIds
    );
}
