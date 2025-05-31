package am.shavigh.api.dto.bibles;

import java.util.ArrayList;
import java.util.List;

public class BibleBookDto {

    private int id;
    private String title;
    private int serialNumber;
    private String translationName;
    private List<BibleBookChapterDto> chapters = new ArrayList<>();

    public BibleBookDto(int id, String title, int serialNumber, String translationName) {
        this.id = id;
        this.title = title;
        this.serialNumber = serialNumber;
        this.translationName = translationName;
    }

    public BibleBookDto() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getTranslationName() {
        return translationName;
    }

    public void setTranslationName(String translationName) {
        this.translationName = translationName;
    }

    public List<BibleBookChapterDto> getChapters() {
        return chapters;
    }

    public void setChapters(List<BibleBookChapterDto> chapters) {
        this.chapters = chapters;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
