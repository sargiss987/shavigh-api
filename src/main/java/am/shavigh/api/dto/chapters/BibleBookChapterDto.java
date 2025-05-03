package am.shavigh.api.dto.chapters;

public class BibleBookChapterDto {

    private String title;
    private String content;
    private String url;
    private String linkToDefaultContent;
    private String nextLink;
    private String prevLink;

    public BibleBookChapterDto(String title, String content, String url, String linkToDefaultContent, String nextLink, String prevLink) {
        this.title = title;
        this.content = content;
        this.url = url;
        this.linkToDefaultContent = linkToDefaultContent;
        this.nextLink = nextLink;
        this.prevLink = prevLink;
    }

    public BibleBookChapterDto() {}

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

    public String getLinkToDefaultContent() {
        return linkToDefaultContent;
    }

    public void setLinkToDefaultContent(String linkToDefaultContent) {
        this.linkToDefaultContent = linkToDefaultContent;
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
}
