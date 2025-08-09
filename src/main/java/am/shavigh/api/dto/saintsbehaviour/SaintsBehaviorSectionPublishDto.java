package am.shavigh.api.dto.saintsbehaviour;

import java.util.ArrayList;
import java.util.List;

public class SaintsBehaviorSectionPublishDto {
    private Long id;
    private Long originId;
    private List<Long> saintsBehaviourSectionAttachedPageIds = new ArrayList<>();

    public SaintsBehaviorSectionPublishDto() {}

    public SaintsBehaviorSectionPublishDto(Long id, Long originId, List<Long> saintsBehaviourSectionAttachedPageIds) {
        this.id = id;
        this.originId = originId;
        this.saintsBehaviourSectionAttachedPageIds = saintsBehaviourSectionAttachedPageIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOriginId() {
        return originId;
    }

    public void setOriginId(Long originId) {
        this.originId = originId;
    }

    public List<Long> getSaintsBehaviourSectionAttachedPageIds() {
        return saintsBehaviourSectionAttachedPageIds;
    }

    public void setSaintsBehaviourSectionAttachedPageIds(List<Long> saintsBehaviourSectionAttachedPageIds) {
        this.saintsBehaviourSectionAttachedPageIds = saintsBehaviourSectionAttachedPageIds;
    }
}
