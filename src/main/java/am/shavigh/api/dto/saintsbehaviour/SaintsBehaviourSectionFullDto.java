package am.shavigh.api.dto.saintsbehaviour;

public class SaintsBehaviourSectionFullDto {

    private Long id;

    private String title;

    private String content;

    private String url;

    private String status;

    private Long originId;
    private Integer saintsBehaviourId;

    public SaintsBehaviourSectionFullDto() {}

    public SaintsBehaviourSectionFullDto(Long id, String title, String content, String url, String status, Long originId, int saintsBehaviourId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.url = url;
        this.status = status;
        this.originId = originId;
        this.saintsBehaviourId = saintsBehaviourId;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Integer getSaintsBehaviourId() {
        return saintsBehaviourId;
    }

    public void setSaintsBehaviourId(Integer saintsBehaviourId) {
        this.saintsBehaviourId = saintsBehaviourId;
    }
}
