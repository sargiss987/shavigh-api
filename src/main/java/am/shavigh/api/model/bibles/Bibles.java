package am.shavigh.api.model.bibles;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Bibles implements Serializable {

    @Id
    private Long id;

    private String name;
    private String uniqueName;

    @OneToMany(mappedBy = "bibles")
    private List<BibleBooks> books;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    public List<BibleBooks> getBooks() {
        return books;
    }

    public void setBooks(List<BibleBooks> books) {
        this.books = books;
    }
}
