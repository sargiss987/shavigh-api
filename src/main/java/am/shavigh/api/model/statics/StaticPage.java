package am.shavigh.api.model.statics;

import jakarta.persistence.*;

@Entity
@Table(name = "static_pages")
public class StaticPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String uniqueName;

    private String status = "publish";

    private String content;

    private Integer originId;

    public StaticPage() {
    }

    public StaticPage(String uniqueName, String status, String content) {
        this.uniqueName = uniqueName;
        this.status = status;
        this.content = content;
    }

    public StaticPage(String uniqueName, String status, String content, Integer originId) {
        this.uniqueName = uniqueName;
        this.status = status;
        this.content = content;
        this.originId = originId;
    }

    public StaticPage(Integer id, String uniqueName, String status, String content, Integer originId) {
        this.id = id;
        this.uniqueName = uniqueName;
        this.status = status;
        this.content = content;
        this.originId = originId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getOriginId() {
        return originId;
    }

    public void setOriginId(Integer originId) {
        this.originId = originId;
    }
}

