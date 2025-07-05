package am.shavigh.api.service;

import am.shavigh.api.dto.bibles.BibleBookChapterDto;
import am.shavigh.api.dto.bibles.BibleBookDto;
import am.shavigh.api.dto.bibles.BibleDto;
import am.shavigh.api.dto.bibles.BibleFlatDto;
import am.shavigh.api.dto.chapters.BiblesChapterPublishDto;
import am.shavigh.api.dto.chapters.CreateBibleBookChapterDto;
import am.shavigh.api.dto.pages.BibleBookChapterPageDto;
import am.shavigh.api.dto.pages.BibleBookChapterPageMinDataDto;
import am.shavigh.api.dto.pages.CreateBibleBookChapterPageDto;
import am.shavigh.api.exceptions.ApiException;
import am.shavigh.api.model.bibles.BibleBookChapterPages;
import am.shavigh.api.model.bibles.BibleBookChapters;
import am.shavigh.api.repo.BibleBookChapterPageRepo;
import am.shavigh.api.repo.BiblesBookChapterRepo;
import am.shavigh.api.repo.BiblesBookRepo;
import am.shavigh.api.repo.BiblesRepo;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BiblesService {

    private final BiblesRepo biblesRepo;
    private final BiblesBookChapterRepo biblesBookChapterRepo;
    private final BibleBookChapterPageRepo bibleBookChapterPageRepo;
    private final BiblesBookRepo biblesBookRepo;

    public BiblesService(BiblesRepo biblesRepo, BiblesBookChapterRepo biblesBookChapterRepo, BibleBookChapterPageRepo bibleBookChapterPageRepo, BiblesBookRepo biblesBookRepo) {
        this.biblesRepo = biblesRepo;
        this.biblesBookChapterRepo = biblesBookChapterRepo;
        this.bibleBookChapterPageRepo = bibleBookChapterPageRepo;
        this.biblesBookRepo = biblesBookRepo;
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
                    var newBook = new BibleBookDto(flat.bookId(), flat.bookTitle(), flat.serialNumber(), flat.translationName());
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

    public am.shavigh.api.dto.chapters.BibleBookChapterDto getBiblesChapterByUrl(String url, String status) {
        var response = biblesRepo.findByUrl(url, status);
        if (response == null) {
            throw new ApiException("Bible chapter not found with URL: " + url, HttpStatus.NOT_FOUND);
        }

        return response;
    }

    public BibleBookChapterPageDto getBiblesChapterPagesByUrl(String url, String status) {
        var response = biblesRepo.findPageByUrl(url, status);

        if (response == null) {
            throw new ApiException("Bible chapter page not found with URL: " + url, HttpStatus.NOT_FOUND);
        }

        return response;
    }

    public am.shavigh.api.dto.chapters.BibleBookChapterDto createBiblesChapter(CreateBibleBookChapterDto createDto) {;
        return biblesBookRepo.findById(createDto.getBibleBookId())
                .map(bibleBook -> {
                    setChapterPagesAttachedStatus(createDto);

                    var chapter = new BibleBookChapters();
                    if (createDto.getId() != null) {
                        chapter.setId(createDto.getId());
                    }
                    chapter.setOriginId(createDto.getOriginId());
                    chapter.setBibleBooks(bibleBook);
                    chapter.setTitle(createDto.getTitle());
                    chapter.setContent(createDto.getContent());
                    chapter.setUrl(createDto.getUrl());
                    chapter.setNextLink(createDto.getNextLink());
                    chapter.setPrevLink(createDto.getPrevLink());
                    chapter.setStatus("draft");

                    var savedChapter = biblesBookChapterRepo.save(chapter);

                    return new am.shavigh.api.dto.chapters.BibleBookChapterDto(
                            savedChapter.getId(),
                            savedChapter.getTitle(),
                            savedChapter.getContent(),
                            savedChapter.getUrl(),
                            savedChapter.getNextLink(),
                            savedChapter.getPrevLink(),
                            savedChapter.getStatus(),
                            savedChapter.getOriginId(),
                            savedChapter.getBibleBooks().getId()
                    );
                })
                .orElseThrow(() -> new NoSuchElementException("Bible book not found with ID: " + createDto.getBibleBookId()));
    }

    private void setChapterPagesAttachedStatus(CreateBibleBookChapterDto createDto) {
        var unattachedPagesIds = createDto.getBibleBookChapterUnattachedPageIds();

        if (unattachedPagesIds != null && !unattachedPagesIds.isEmpty()) {
            var unattachedPages = bibleBookChapterPageRepo.findByIdIn(unattachedPagesIds);
            unattachedPages.forEach(page -> page.setAttached(false));
            bibleBookChapterPageRepo.saveAll(unattachedPages);

            var attachedPages = bibleBookChapterPageRepo.findByIdNotIn(unattachedPagesIds);
            attachedPages.forEach(page -> page.setAttached(true));
            bibleBookChapterPageRepo.saveAll(attachedPages);
        } else {
            var allPages = bibleBookChapterPageRepo.findAll();
            allPages.forEach(page -> page.setAttached(true));
            bibleBookChapterPageRepo.saveAll(allPages);
        }
    }

    public BibleBookChapterPageDto createOrUpdateBiblesChapterPage(CreateBibleBookChapterPageDto createBibleBookChapterPageDto) {
        return biblesBookChapterRepo.findById(createBibleBookChapterPageDto.getBibleBookChapterId())
                .map(chapter -> {
                    var page = new BibleBookChapterPages();
                    if (createBibleBookChapterPageDto.getId() != null) {
                        page.setId(createBibleBookChapterPageDto.getId());
                    } else {
                        page.setAttached(false);
                    }
                    page.setTitle(createBibleBookChapterPageDto.getTitle());
                    page.setBibleBookChapters(chapter);
                    page.setContent(createBibleBookChapterPageDto.getContent());
                    page.setUrl(createBibleBookChapterPageDto.getUrl());
                    page.setNextLink(createBibleBookChapterPageDto.getNextLink());
                    page.setPrevLink(createBibleBookChapterPageDto.getPrevLink());
                    page.setOriginId(createBibleBookChapterPageDto.getOriginId());
                    page.setStatus("draft");

                    var savedPage = bibleBookChapterPageRepo.save(page);

                    return new BibleBookChapterPageDto(
                            savedPage.getId(),
                            savedPage.getTitle(),
                            savedPage.getContent(),
                            savedPage.getUrl(),
                            savedPage.getNextLink(),
                            savedPage.getPrevLink(),
                            savedPage.getStatus(),
                            savedPage.getOriginId(),
                            savedPage.getBibleBookChapters().getId()
                    );
                })
                .orElseThrow(() -> new NoSuchElementException("Bible book chapter not found with ID: " + createBibleBookChapterPageDto.getBibleBookChapterId()));
    }

    @Transactional
    public void publishBiblesChapter(BiblesChapterPublishDto biblesChapterPublishDto) {
        biblesBookChapterRepo.findById(biblesChapterPublishDto.getId())
                .ifPresentOrElse(chapter -> {
                    if (biblesChapterPublishDto.getOriginId() == null) {
                        // No origin -> publish current chapter
                        chapter.setStatus("publish");
                        biblesBookChapterRepo.save(chapter);
                    } else {
                        // Has origin -> update original and remove draft
                        biblesBookChapterRepo.findById(biblesChapterPublishDto.getOriginId())
                                .ifPresentOrElse(original -> {
                                    // Update original with data from draft
                                    original.setStatus("publish");
                                    original.setTitle(chapter.getTitle());
                                    original.setContent(chapter.getContent());
                                    original.setUrl(chapter.getUrl());
                                    original.setNextLink(chapter.getNextLink());
                                    original.setPrevLink(chapter.getPrevLink());
                                    biblesBookChapterRepo.save(original);

                                    // Delete the draft chapter
                                    biblesBookChapterRepo.delete(chapter);
                                }, () -> {
                                    throw new NoSuchElementException("Original Bible chapter not found with ID: " + biblesChapterPublishDto.getOriginId());
                                });
                    }
                }, () -> {
                    throw new NoSuchElementException("Bible chapter not found with ID: " + biblesChapterPublishDto.getId());
                });
    }

    @Transactional
    public void publishBiblesChapterPage(BiblesChapterPublishDto biblesChapterPublishDto) {
        System.out.println("Publishing chapter page: " + biblesChapterPublishDto);
        bibleBookChapterPageRepo.findById(biblesChapterPublishDto.getId())
                .ifPresentOrElse(page -> {
                    if (biblesChapterPublishDto.getOriginId() == null) {
                        // No origin -> publish current page
                        page.setStatus("publish");
                        bibleBookChapterPageRepo.save(page);
                    } else {
                        // Has origin -> update original and remove draft
                        bibleBookChapterPageRepo.findById(biblesChapterPublishDto.getOriginId())
                                .ifPresentOrElse(original -> {
                                    // Update original with data from draft
                                    original.setStatus("publish");
                                    original.setTitle(page.getTitle());
                                    original.setContent(page.getContent());
                                    original.setUrl(page.getUrl());
                                    original.setNextLink(page.getNextLink());
                                    original.setPrevLink(page.getPrevLink());
                                    bibleBookChapterPageRepo.save(original);

                                    // Delete the draft page
                                    bibleBookChapterPageRepo.delete(page);
                                }, () -> {
                                    throw new NoSuchElementException("Original Bible chapter page not found with ID: " + biblesChapterPublishDto.getOriginId());
                                });
                    }
                }, () -> {
                    throw new NoSuchElementException("Bible chapter page not found with ID: " + biblesChapterPublishDto.getId());
                });
    }

    public List<am.shavigh.api.dto.chapters.BibleBookChapterDto> findByStatusIsDraft(String status) {
        return biblesBookChapterRepo.findByStatus(status)
                .stream()
                .map(chapter -> new am.shavigh.api.dto.chapters.BibleBookChapterDto(
                        chapter.getId(),
                        chapter.getTitle(),
                        chapter.getContent(),
                        chapter.getUrl(),
                        chapter.getNextLink(),
                        chapter.getPrevLink(),
                        chapter.getStatus(),
                        chapter.getOriginId(),
                        chapter.getBibleBooks().getId()))
                .toList();
    }

    public List<BibleBookChapterPageDto> findDraftBiblesChapterPages(String draft) {
        return bibleBookChapterPageRepo.findByStatus(draft)
                .stream()
                .map(page -> new BibleBookChapterPageDto(
                        page.getId(),
                        page.getTitle(),
                        page.getContent(),
                        page.getUrl(),
                        page.getNextLink(),
                        page.getPrevLink(),
                        page.getStatus(),
                        page.getOriginId(),
                        page.getBibleBookChapters().getId()))
                .toList();
    }

    public List<BibleBookChapterPageMinDataDto> getBiblesChapterPagesByChapterId(Long chapterId, String status) {
        return bibleBookChapterPageRepo.findByBibleBookChaptersIdAndStatus(chapterId, status)
                .stream()
                .map(page -> new BibleBookChapterPageMinDataDto(
                        page.getId(),
                        page.getTitle(),
                        page.getUrl(),
                        page.getStatus(),
                        page.getAttached()))
                .toList();
    }
}
