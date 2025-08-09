package am.shavigh.api.service;

import am.shavigh.api.dto.chapters.BibleBookChapterDto;
import am.shavigh.api.repo.BiblesRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final BiblesRepo biblesRepo;

    public SearchService(BiblesRepo biblesRepo) {
        this.biblesRepo = biblesRepo;
    }

    public List<BibleBookChapterDto> searchByWord(String word) {
        Pageable limit10 = PageRequest.of(0, 10);
        return biblesRepo.searchByWord(word, limit10);
    }
}
