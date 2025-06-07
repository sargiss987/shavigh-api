package am.shavigh.api.service;

import am.shavigh.api.dto.saintsbehaviour.SaintsBehaviorFlatDto;
import am.shavigh.api.dto.saintsbehaviour.SaintsBehaviourDto;
import am.shavigh.api.dto.saintsbehaviour.SaintsBehaviourSectionDto;
import am.shavigh.api.repo.SaintsBehaviorRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaintsBehaviorService {

    private final SaintsBehaviorRepo saintsBehaviorRepo;

    public SaintsBehaviorService(SaintsBehaviorRepo saintsBehaviorRepo) {
        this.saintsBehaviorRepo = saintsBehaviorRepo;
    }

    public List<SaintsBehaviourDto> getSaintsBehaviorWithSections() {
        List<SaintsBehaviorFlatDto> flatList = saintsBehaviorRepo.getSaintsBehaviorWithSections();
        return mapToNestedDtos(flatList);
    }

    public List<SaintsBehaviourDto> mapToNestedDtos(List<SaintsBehaviorFlatDto> flatList) {
        Map<Integer, SaintsBehaviourDto> dtoMap = new LinkedHashMap<>();

        for (SaintsBehaviorFlatDto flat : flatList) {
            SaintsBehaviourDto parent = dtoMap.computeIfAbsent(flat.getId(), id -> new SaintsBehaviourDto(
                    flat.getId(),
                    flat.getTitle(),
                    flat.getUrl(),
                    flat.getStatus(),
                    new ArrayList<>()
            ));

            SaintsBehaviourSectionDto sectionDto = new SaintsBehaviourSectionDto();
            sectionDto.setId(flat.getSectionId());
            sectionDto.setTitle(flat.getSectionTitle());
            sectionDto.setContent(flat.getSectionContent());
            sectionDto.setUrl(flat.getSectionUrl());
            sectionDto.setStatus(flat.getSectionStatus());

            parent.getSections().add(sectionDto);
        }

        return new ArrayList<>(dtoMap.values());
    }

}
