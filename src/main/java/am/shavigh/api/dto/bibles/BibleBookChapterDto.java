package am.shavigh.api.dto.bibles;

public class BibleBookChapterDto {
    private String title;
    private String url;

    public BibleBookChapterDto(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public BibleBookChapterDto() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
