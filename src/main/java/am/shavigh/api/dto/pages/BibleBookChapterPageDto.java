package am.shavigh.api.dto.pages;

public class BibleBookChapterPageDto {

    private String title;
    private String content;
    private String url;
    private String nextLink;
    private String prevLink;
    private String status;

    public BibleBookChapterPageDto(String title, String content, String url, String nextLink, String prevLink, String status) {
        this.title = title;
        this.content = content;
        this.url = url;
        this.nextLink = nextLink;
        this.prevLink = prevLink;
        this.status = status;
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
}
