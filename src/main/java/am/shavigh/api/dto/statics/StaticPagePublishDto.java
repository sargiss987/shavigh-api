package am.shavigh.api.dto.statics;

public class StaticPagePublishDto {

    private Integer id;
    private Integer originId;

    public StaticPagePublishDto() {
    }

    public StaticPagePublishDto(Integer id, Integer originId) {
        this.id = id;
        this.originId = originId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOriginId() {
        return originId;
    }

    public void setOriginId(Integer originId) {
        this.originId = originId;
    }
}
