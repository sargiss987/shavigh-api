package am.shavigh.api.dto;

import java.util.ArrayList;
import java.util.List;

public class BibleDto {

    private String name;
    private List<BibleBookDto> books = new ArrayList<>();

    public BibleDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BibleBookDto> getBooks() {
        return books;
    }

    public void setBooks(List<BibleBookDto> books) {
        this.books = books;
    }
}
