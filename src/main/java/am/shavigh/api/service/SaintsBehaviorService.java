package am.shavigh.api.service;

import am.shavigh.api.dto.saintsbehaviour.*;
import am.shavigh.api.exceptions.ApiException;
import am.shavigh.api.model.saintsbehavior.SaintsBehaviorSection;
import am.shavigh.api.model.saintsbehavior.SaintsBehaviorSectionPage;
import am.shavigh.api.repo.SaintsBehaviorRepo;
import am.shavigh.api.repo.SaintsBehaviorSectionPageRepo;
import am.shavigh.api.repo.SaintsBehaviourSectionRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SaintsBehaviorService {

    private final SaintsBehaviorRepo saintsBehaviorRepo;
    private final SaintsBehaviorSectionPageRepo saintsBehaviorSectionPageRepo;
    private final SaintsBehaviourSectionRepo saintsBehaviorSectionRepo;

    public SaintsBehaviorService(SaintsBehaviorRepo saintsBehaviorRepo, SaintsBehaviorSectionPageRepo saintsBehaviorSectionPageRepo, SaintsBehaviourSectionRepo saintsBehaviorSectionRepo) {
        this.saintsBehaviorRepo = saintsBehaviorRepo;
        this.saintsBehaviorSectionPageRepo = saintsBehaviorSectionPageRepo;
        this.saintsBehaviorSectionRepo = saintsBehaviorSectionRepo;
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
            sectionDto.setUrl(flat.getSectionUrl());
            parent.getSections().add(sectionDto);
        }

        return new ArrayList<>(dtoMap.values());
    }

    public SaintsBehaviourSectionFullDto getSaintsBehaviorSectionByUrl(String url, String status) {
        var response =  saintsBehaviorRepo.findByUrl(url, status);
        if (response == null) {
            throw new ApiException("Saints Behaviour not found with URL: " + url, HttpStatus.NOT_FOUND);
        }

        return response;
    }

    public SaintsBehaviorSectionPageDto getSaintsBehaviorSectionPageByUrl(String url, String status) {
        var response =  saintsBehaviorSectionPageRepo.findByUrl(url, status);
        if (response == null) {
            throw new ApiException("Saints Behaviour Page not found with URL: " + url, HttpStatus.NOT_FOUND);
        }

        return response;
    }

    public SaintsBehaviourSectionFullDto createSaintsBehaviorSection(CreateSaintsBehaviourSectionDto createDto) {
        return saintsBehaviorRepo.findById(createDto.getSaintsBehaviourId())
                .map(saintsBehavior -> {
                    var section = new SaintsBehaviorSection();

                    if (createDto.getId() != null) {
                        section.setId(createDto.getId());
                    }

                    section.setSaintsBehavior(saintsBehavior);
                    section.setOriginId(createDto.getOriginId());
                    section.setTitle(createDto.getTitle());
                    section.setContent(createDto.getContent());
                    section.setUrl(createDto.getUrl());
                    section.setStatus("draft");

                    var savedSection = saintsBehaviorSectionRepo.save(section);

                    return new SaintsBehaviourSectionFullDto(
                            savedSection.getId(),
                            savedSection.getTitle(),
                            savedSection.getContent(),
                            savedSection.getUrl(),
                            savedSection.getStatus(),
                            savedSection.getOriginId(),
                            savedSection.getSaintsBehavior().getId()
                    );
                })
                .orElseThrow(() -> new NoSuchElementException("Saints behavior not found with ID: " + createDto.getSaintsBehaviourId()));
    }

    public SaintsBehaviorSectionPageDto createSaintsBehaviorSectionPage(CreateBehaviorSectionPageDto createDto) {
        return saintsBehaviorSectionRepo.findById(createDto.getSaintsBehaviorSectionId())
                .map(section -> {
                    var page = new SaintsBehaviorSectionPage();

                    if (createDto.getId() != null) {
                        page.setId(createDto.getId());
                    }

                    page.setSaintsBehaviorSection(section);
                    page.setOriginId(createDto.getOriginId());
                    page.setTitle(createDto.getTitle());
                    page.setContent(createDto.getContent());
                    page.setUrl(createDto.getUrl());
                    page.setStatus("draft");

                    var savedPage = saintsBehaviorSectionPageRepo.save(page);

                    return new SaintsBehaviorSectionPageDto(
                            savedPage.getId(),
                            savedPage.getTitle(),
                            savedPage.getContent(),
                            savedPage.getUrl(),
                            savedPage.getStatus(),
                            savedPage.getOriginId(),
                            savedPage.getSaintsBehaviorSection().getId()
                    );
                })
                .orElseThrow(() -> new NoSuchElementException("Saints behavior section not found with ID: " + createDto.getSaintsBehaviorSectionId()));
    }

}
