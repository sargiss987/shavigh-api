package am.shavigh.api.model.inbox;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "in_box")
public class InBox {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate localDate;
    private String content;
    private String url;

    // Constructors
    public InBox() {
    }

    public InBox(LocalDate localDate, String content, String url) {
        this.localDate = localDate;
        this.content = content;
        this.url = url;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
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
}

