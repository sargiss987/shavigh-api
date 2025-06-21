package am.shavigh.api.dto.chapters;

public class BibleBookChapterDto {

    private Long id;
    private String title;
    private String content;
    private String url;
    private String nextLink;
    private String prevLink;
    private String status;
    private Long originId;
    private Long bibleBookId;

    public BibleBookChapterDto(Long id, String title, String content, String url, String nextLink, String prevLink, String status, Long originId, Long bibleBookId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.url = url;
        this.nextLink = nextLink;
        this.prevLink = prevLink;
        this.status = status;
        this.originId = originId;
        this.bibleBookId = bibleBookId;
    }

    public BibleBookChapterDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
