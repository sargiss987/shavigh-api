package am.shavigh.api.dto.bibles;

public record BibleFlatDto(
        String bibleName,
        String bibleUniqueName,
        String bookTitle,
        int serialNumber,
        String translationName,
        String chapterTitle,
        String chapterUrl
) {
}
