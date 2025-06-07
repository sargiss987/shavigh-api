package am.shavigh.api.dto.statics;

public class StaticPageUpdateDto {

    private Integer id;
    private String uniqueName;
    private String content;
    private Integer originId;

    public StaticPageUpdateDto() {
    }

    public StaticPageUpdateDto(Integer id, String uniqueName, String content, Integer originId) {
        this.id = id;
        this.uniqueName = uniqueName;
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