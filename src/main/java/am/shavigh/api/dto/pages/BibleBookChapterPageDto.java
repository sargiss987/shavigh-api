package am.shavigh.api.dto.pages;

public class BibleBookChapterPageDto {

    private Long id;
    private String title;
    private String content;
    private String url;
    private String nextLink;
    private String prevLink;
    private String status;
    private Long originId;
    private Long bibleBookChapterId;
    private Boolean attached;

    public BibleBookChapterPageDto(Long id,String title, String content, String url, String nextLink, String prevLink, String status, Long originId, Long bibleBookChapterId, Boolean attached) {
        this.title = title;
        this.content = content;
        this.url = url;
        this.nextLink = nextLink;
        this.prevLink = prevLink;
        this.status = status;
        this.id = id;
        this.originId = originId;
        this.bibleBookChapterId = bibleBookChapterId;
        this.attached = attached;
    }

    public BibleBookChapterPageDto() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNextLink() {
        return nextLink;
    }

    public void setNextLink(String nextLink) {
        this.nextLink = nextLink;
    }

    public String getPrevLink() {
        return prevLink;
    }

    public void setPrevLink(String prevLink) {
        this.prevLink = prevLink;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Long getBibleBookChapterId() {
        return bibleBookChapterId;
    }

    public void setBibleBookChapterId(Long bibleBookChapterId) {
        this.bibleBookChapterId = bibleBookChapterId;
    }

    public Boolean getAttached() {
        return attached;
    }

    public void setAttached(Boolean attached) {
        this.attached = attached;
    }
}
