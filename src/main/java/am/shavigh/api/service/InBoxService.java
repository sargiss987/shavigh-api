package am.shavigh.api.service;

import am.shavigh.api.dto.CreateInBoxDto;
import am.shavigh.api.model.inbox.InBox;
import am.shavigh.api.repo.InBoxRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InBoxService {

    private final InBoxRepo inBoxRepo;

    public InBoxService(InBoxRepo inBoxRepo) {
        this.inBoxRepo = inBoxRepo;
    }

    public void createInBox(CreateInBoxDto createInBoxDto) {
        var inBox = new InBox(LocalDate.now(), createInBoxDto.getContent(), createInBoxDto.getUrl());
        inBoxRepo.save(inBox);
    }

    public List<InBox> getAllInBoxes() {
        return inBoxRepo.getFirst10ThousandByOrderByLocalDateDesc();
    }

    public void deleteInBox(Long id) {
        inBoxRepo.deleteById(id);
    }
}
