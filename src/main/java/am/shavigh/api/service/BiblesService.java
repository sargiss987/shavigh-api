package am.shavigh.api.service;

import am.shavigh.api.dto.bibles.BibleBookChapterDto;
import am.shavigh.api.dto.bibles.BibleBookDto;
import am.shavigh.api.dto.bibles.BibleDto;
import am.shavigh.api.dto.bibles.BibleFlatDto;
import am.shavigh.api.repo.BiblesRepo;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BiblesService {

    private final BiblesRepo biblesRepo;

    public BiblesService(BiblesRepo biblesRepo) {
        this.biblesRepo = biblesRepo;
    }

    public List<BibleDto> getBibleDtoList() {
        var bibleFlatDtoList = biblesRepo.getBibleDtoList();
        var bibleMap = groupFlatDtos(bibleFlatDtoList);
        sortBooksAndChapters(bibleMap);
        return new ArrayList<>(bibleMap.values());
    }

    private Map<String, BibleDto> groupFlatDtos(List<BibleFlatDto> flatList) {
        var bibleMap = new LinkedHashMap<String, BibleDto>();

        for (BibleFlatDto flat : flatList) {
            var bible = bibleMap.computeIfAbsent(flat.bibleName(),
                    name -> new BibleDto(name, flat.bibleUniqueName()));

            var book = findOrCreateBook(bible, flat);
            book.getChapters().add(new BibleBookChapterDto(flat.chapterTitle(), flat.chapterUrl()));
        }

        return bibleMap;
    }

    private BibleBookDto findOrCreateBook(BibleDto bible, BibleFlatDto flat) {
        return bible.getBooks().stream()
                .filter(b -> b.getTitle().equals(flat.bookTitle()) &&
                        b.getSerialNumber() == flat.serialNumber() &&
                        b.getTranslationName().equals(flat.translationName()))
                .findFirst()
                .orElseGet(() -> {
                    var newBook = new BibleBookDto(flat.bookTitle(), flat.serialNumber(), flat.translationName());
                    bible.getBooks().add(newBook);
                    return newBook;
                });
    }

    private void sortBooksAndChapters(Map<String, BibleDto> bibleMap) {
        Comparator<BibleBookDto> bookComparator = Comparator
                .comparingInt(BibleBookDto::getSerialNumber)
                .thenComparingInt(b -> switch (b.getTranslationName()) {
                    case "Էջմիածին" -> 1;
                    case "Արարատ" -> 2;
                    case "Գրաբար" -> 3;
                    case "ru" -> 4;
                    default -> 99;
                });

        Comparator<BibleBookChapterDto> chapterComparator = Comparator.comparingInt((BibleBookChapterDto ch) -> {
            String title = ch.getTitle();
            if ("Ներածութիւն".equals(title)) return 0;

            try {
                String numericPart = title.replaceAll("[^0-9]", "");
                return Integer.parseInt(numericPart);
            } catch (NumberFormatException e) {
                return 9999;
            }
        }).thenComparing(ch -> {
            String title = ch.getTitle();
            return title.matches("^\\d+[Ա-Ֆա-ֆ]+$") ? title : "";
        });

        for (BibleDto bible : bibleMap.values()) {
            bible.getBooks().sort(bookComparator);
            bible.getBooks().forEach(book -> book.getChapters().sort(chapterComparator));
        }
    }

    public am.shavigh.api.dto.chapters.BibleBookChapterDto getBiblesChapterByUrl(String url) {
        return biblesRepo.findByUrl(url);
    }
}
