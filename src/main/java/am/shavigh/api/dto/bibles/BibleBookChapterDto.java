package am.shavigh.api.dto.bibles;

public class BibleBookChapterDto {
    private Integer id;
    private String title;
    private String url;

    public BibleBookChapterDto(Integer id, String title, String url) {
        this.id = id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
