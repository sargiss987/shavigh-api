package am.shavigh.api.dto;

public record BibleFlatDto(
        String bibleName,
        String bookTitle,
        int serialNumber,
        String translationName,
        String chapterTitle,
        String chapterUrl
) {
}
