package am.shavigh.api.dto.saintsbehaviour;

public class SaintsBehaviorSectionPublishDto {
    private Long id;
    private Long originId;

    public SaintsBehaviorSectionPublishDto() {}

    public SaintsBehaviorSectionPublishDto(Long id, Long originId) {
        this.id = id;
        this.originId = originId;
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
}
