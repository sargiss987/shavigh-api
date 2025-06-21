package am.shavigh.api.dto.chapters;

public class BiblesChapterPublishDto {

    private Long id;
    private Long originId;

    public BiblesChapterPublishDto() {
    }

    public BiblesChapterPublishDto(Long id, Long originId) {
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

    @Override
    public String toString() {
        return "BiblesChapterPublishDto{" +
                "id=" + id +
                ", originId=" + originId +
                '}';
    }
}
