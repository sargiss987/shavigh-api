package am.shavigh.api.dto.saintsbehaviour;

public class SaintsBehaviorSectionPageMinDataDto {
    private Integer id;
    private String title;
    private String url;
    private String status;
    private Boolean attached;

    // Constructors
    public SaintsBehaviorSectionPageMinDataDto() {}

    public SaintsBehaviorSectionPageMinDataDto(Integer id, String title, String url, String status, Boolean attached) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.status = status;
        this.attached = attached;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Boolean getAttached() {
        return attached;
    }

    public void setAttached(Boolean attached) {
        this.attached = attached;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}