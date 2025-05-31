package am.shavigh.api.dto.chapters;

public class CreateBibleBookChapterDto {

    private String title;
    private String content;
    private String url;
    private String nextLink;
    private String prevLink;
    private int bookId;

    public CreateBibleBookChapterDto(String title, String content, String url, String nextLink, String prevLink, int bookId) {
        this.title = title;
        this.content = content;
        this.url = url;
        this.nextLink = nextLink;
        this.prevLink = prevLink;
        this.bookId = bookId;
    }

    public CreateBibleBookChapterDto() {}

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

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
