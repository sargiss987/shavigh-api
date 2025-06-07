package am.shavigh.api.dto.saintsbehaviour;

import java.util.List;

public class SaintsBehaviourDto {

    private Integer id;
    private String title;
    private String url;
    private String status;
    private List<SaintsBehaviourSectionDto> sections;

    public SaintsBehaviourDto() {
    }

    public SaintsBehaviourDto(Integer id, String title, String url, String status, List<SaintsBehaviourSectionDto> sections) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.status = status;
        this.sections = sections;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SaintsBehaviourSectionDto> getSections() {
        return sections;
    }

    public void setSections(List<SaintsBehaviourSectionDto> sections) {
        this.sections = sections;
    }
}
