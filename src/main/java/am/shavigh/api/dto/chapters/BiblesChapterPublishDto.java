package am.shavigh.api.dto.chapters;

public class BiblesChapterPublishDto {

    private Long id;
    private Long originId;
    private Long bibleBookId;

    public BiblesChapterPublishDto() {
    }

    public BiblesChapterPublishDto(Long id, Long originId, Long bibleBookId) {
        this.id = id;
        this.originId = originId;
        this.bibleBookId = bibleBookId;
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

    public Long getBibleBookId() {
        return bibleBookId;
    }

    public void setBibleBookId(Long bibleBookId) {
        this.bibleBookId = bibleBookId;
    }

    @Override
    public String toString() {
        return "BiblesChapterPublishDto{" +
                "id=" + id +
                ", originId=" + originId +
                '}';
    }
}
