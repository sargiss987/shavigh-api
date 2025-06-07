package am.shavigh.api.dto.statics;

public class StaticPageCreateDto {

    private String uniqueName;
    private String content;

    public StaticPageCreateDto() {
    }

    public StaticPageCreateDto(String uniqueName, String content) {
        this.uniqueName = uniqueName;
        this.content = content;
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
}
