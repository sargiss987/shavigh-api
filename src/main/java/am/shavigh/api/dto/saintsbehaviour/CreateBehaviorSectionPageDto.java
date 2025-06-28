package am.shavigh.api.dto.saintsbehaviour;

public class CreateBehaviorSectionPageDto {
    private Integer id;
    private Long originId;
    private String title;
    private String content;
    private String url;
    private Long saintsBehaviorSectionId;

    // Constructors
    public CreateBehaviorSectionPageDto() {
    }

    public CreateBehaviorSectionPageDto(Integer id, Long originId, String title, String content, String url, Long saintsBehaviorSectionId) {
        this.id = id;
        this.originId = originId;
        this.title = title;
        this.content = content;
        this.url = url;
        this.saintsBehaviorSectionId = saintsBehaviorSectionId;
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

    public Long getSaintsBehaviorSectionId() {
        return saintsBehaviorSectionId;
    }

    public void setSaintsBehaviorSectionId(Long saintsBehaviorSectionId) {
        this.saintsBehaviorSectionId = saintsBehaviorSectionId;
    }
}
