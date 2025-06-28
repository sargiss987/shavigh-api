package am.shavigh.api.dto.saintsbehaviour;

public class SaintsBehaviourSectionDto {

    private Long id;

    private String title;

    private String url;

    public SaintsBehaviourSectionDto() {}

    public SaintsBehaviourSectionDto(Long id, String title) {
        this.id = id;
        this.title = title;
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

}
