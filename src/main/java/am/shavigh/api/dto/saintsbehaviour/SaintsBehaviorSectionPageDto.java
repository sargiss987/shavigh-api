package am.shavigh.api.dto.saintsbehaviour;

public class SaintsBehaviorSectionPageDto {
    private Integer id;
    private String title;
    private String content;
    private String url;
    private String status = "publish";
    private Long originId;
    private Long sectionId;
    private Boolean attached;

    // Constructors
    public SaintsBehaviorSectionPageDto() {
    }

    public SaintsBehaviorSectionPageDto(Integer id, String title, String content, String url, String status, Long originId, Long sectionId, Boolean attached) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.url = url;
        this.status = status;
        this.originId = originId;
        this.sectionId = sectionId;
        this.attached = attached;
    }

    // Getters and setters
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

    public Long getOriginId() {
        return originId;
    }

    public void setOriginId(Long originId) {
        this.originId = originId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Boolean getAttached() {
        return attached;
    }

    public void setAttached(Boolean attached) {
        this.attached = attached;
    }
}
