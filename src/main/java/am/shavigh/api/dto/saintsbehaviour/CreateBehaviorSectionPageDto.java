package am.shavigh.api.dto.saintsbehaviour;

public class CreateBehaviorSectionPageDto {
    private Integer id;
    private Long originId;
    private String title;
    private String content;
    private String url;
    private Long sectionId;
    private Boolean attached;

    // Constructors
    public CreateBehaviorSectionPageDto() {
    }

    public CreateBehaviorSectionPageDto(Integer id, Long originId, String title, String content, String url, Long sectionId, Boolean attached) {
        this.id = id;
        this.originId = originId;
        this.title = title;
        this.content = content;
        this.url = url;
        this.sectionId = sectionId;
        this.attached = attached;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getOriginId() {
        return originId;
    }

    public void setOriginId(Long originId) {
        this.originId = originId;
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

    @Override
    public String toString() {
        return "CreateBehaviorSectionPageDto{" +
                "id=" + id +
                ", originId=" + originId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                ", sectionId=" + sectionId +
                ", attached=" + attached +
                '}';
    }
}
