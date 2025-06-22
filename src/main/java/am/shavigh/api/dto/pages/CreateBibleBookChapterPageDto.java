package am.shavigh.api.dto.pages;

public class CreateBibleBookChapterPageDto {

    private Long id;
    private Long originId;
    private String title;
    private String content;
    private String url;
    private String nextLink;
    private String prevLink;
    private Long bibleBookChapterId;

    public CreateBibleBookChapterPageDto(Long id,Long originId, String title, String content, String url, String nextLink, String prevLink, Long bibleBookChapterId) {
        this.title = title;
        this.content = content;
        this.url = url;
        this.nextLink = nextLink;
        this.prevLink = prevLink;
        this.id = id;
        this.originId = originId;
        this.bibleBookChapterId = bibleBookChapterId;
    }

    public CreateBibleBookChapterPageDto() {}

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
}
