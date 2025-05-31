package am.shavigh.api.dto.bibles;

public record BibleFlatDto(
        String bibleName,
        String bibleUniqueName,
        int bookId,
        String bookTitle,
        int serialNumber,
        String translationName,
        String chapterTitle,
        String chapterUrl
) {
}
