package am.shavigh.api.dto.chapters;

import java.util.ArrayList;
import java.util.List;

public class BiblesChapterPublishDto {

    private Long id;
    private Long originId;
    private Long bibleBookId;
    private List<Long> bibleBookChapterAttachedPageIds = new ArrayList<>();

    public BiblesChapterPublishDto() {
    }

    public BiblesChapterPublishDto(Long id, Long originId, Long bibleBookId, List<Long> bibleBookChapterAttachedPageIds) {
        this.id = id;
        this.originId = originId;
        this.bibleBookId = bibleBookId;
        this.bibleBookChapterAttachedPageIds = bibleBookChapterAttachedPageIds;
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

    public List<Long> getBibleBookChapterAttachedPageIds() {
        return bibleBookChapterAttachedPageIds;
    }

    public void setBibleBookChapterAttachedPageIds(List<Long> bibleBookChapterAttachedPageIds) {
        this.bibleBookChapterAttachedPageIds = bibleBookChapterAttachedPageIds;
    }
}
