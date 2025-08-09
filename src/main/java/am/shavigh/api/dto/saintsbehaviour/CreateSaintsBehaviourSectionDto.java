package am.shavigh.api.dto.saintsbehaviour;

public class CreateSaintsBehaviourSectionDto {

    private Long id;
    private Long originId;
    private String title;
    private String content;
    private String url;
    private Long saintsBehaviourId;


    public CreateSaintsBehaviourSectionDto() {}

    public CreateSaintsBehaviourSectionDto(Long id, Long originId, String title, String content, String url, Long saintsBehaviourId) {
        this.id = id;
        this.originId = originId;
        this.title = title;
        this.content = content;
        this.url = url;
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

    public Long getOriginId() {
        return originId;
    }

    public void setOriginId(Long originId) {
        this.originId = originId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getSaintsBehaviourId() {
        return saintsBehaviourId;
    }

    public void setSaintsBehaviourId(Long saintsBehaviourId) {
        this.saintsBehaviourId = saintsBehaviourId;
    }

    @Override
    public String toString() {
        return "CreateSaintsBehaviourSectionDto{" +
                "id=" + id +
                ", originId=" + originId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", url='" + url + '\'' +
                ", saintsBehaviourId=" + saintsBehaviourId +
                '}';
    }
}
