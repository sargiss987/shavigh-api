package am.shavigh.api.dto.saintsbehaviour;

public class SaintsBehaviourSectionDto {

    private Long id;

    private String title;

    private String content;

    private String url;

    private String status;

    public SaintsBehaviourSectionDto() {}

    public SaintsBehaviourSectionDto(Long id, String title, String content, String url, String status) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.url = url;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
