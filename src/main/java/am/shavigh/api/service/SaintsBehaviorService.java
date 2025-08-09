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
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public List<SaintsBehaviourDto> getSaintsBehaviorWithSections(String status) {
        List<SaintsBehaviorFlatDto> flatList = saintsBehaviorRepo.getSaintsBehaviorWithSections(status);
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
        var response = saintsBehaviorRepo.findByUrl(url, status);
        if (response == null) {
            throw new ApiException("Saints Behaviour not found with URL: " + url, HttpStatus.NOT_FOUND);
        }

        return response;
    }

    public SaintsBehaviorSectionPageDto getSaintsBehaviorSectionPageByUrl(String url, String status) {
        var response = saintsBehaviorSectionPageRepo.findByUrl(url, status);
        if (response == null) {
            throw new ApiException("Saints Behaviour Page not found with URL: " + url, HttpStatus.NOT_FOUND);
        }

        return response;
    }

    public SaintsBehaviourSectionFullDto createSaintsBehaviorSection(CreateSaintsBehaviourSectionDto createDto) {
        System.out.println(createDto);
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

    private void setSectionPagesStatus(SaintsBehaviorSectionPublishDto publishDto) {
        var attachedPageIds = publishDto.getSaintsBehaviourSectionAttachedPageIds();

        if (attachedPageIds != null && !attachedPageIds.isEmpty()) {
            // Fetch attached pages and index by id
            var attachedPages = saintsBehaviorSectionPageRepo.findByIdIn(attachedPageIds);

            // Mark attached = true
            attachedPages.forEach(p -> p.setAttached(true));


            saintsBehaviorSectionPageRepo.saveAll(attachedPages);

            // Detach the rest in this section
            var unattached = saintsBehaviorSectionPageRepo
                    .findBySectionIdAndIdsNotIn(publishDto.getOriginId(), attachedPageIds); // <- use getId() if that's your field
            unattached.forEach(p -> p.setAttached(false));
            saintsBehaviorSectionPageRepo.saveAll(unattached);

        } else {
            // No attached IDs -> mark all pages in the section as unattached
            var allPages = saintsBehaviorSectionPageRepo
                    .findAllBySaintsBehaviorSectionId(publishDto.getOriginId()); // <- swap to getId() if needed
            allPages.forEach(p -> p.setAttached(false));
            saintsBehaviorSectionPageRepo.saveAll(allPages);
        }
    }

    public SaintsBehaviorSectionPageDto createSaintsBehaviorSectionPage(CreateBehaviorSectionPageDto createDto) {
        System.out.println(createDto);
        return saintsBehaviorSectionRepo.findById(createDto.getSectionId())
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
                    page.setAttached(false);

                    var savedPage = saintsBehaviorSectionPageRepo.save(page);

                    return new SaintsBehaviorSectionPageDto(
                            savedPage.getId(),
                            savedPage.getTitle(),
                            savedPage.getContent(),
                            savedPage.getUrl(),
                            savedPage.getStatus(),
                            savedPage.getOriginId(),
                            savedPage.getSaintsBehaviorSection().getId(),
                            savedPage.getAttached()
                    );
                })
                .orElseThrow(() -> new NoSuchElementException("Saints behavior section not found with ID: " + createDto.getSectionId()));
    }

    @Transactional
    public void publishSaintsBehaviorSection(SaintsBehaviorSectionPublishDto publishDto) {
        saintsBehaviorSectionRepo.findById(publishDto.getId())
                .ifPresentOrElse(section -> {
                    if (publishDto.getOriginId() == null) {
                        // No origin -> publish current section
                        section.setStatus("publish");
                        saintsBehaviorSectionRepo.save(section);
                    } else {
                        // Has origin -> update original and remove draft
                        saintsBehaviorSectionRepo.findById(publishDto.getOriginId())
                                .ifPresentOrElse(original -> {
                                    // Update original with data from draft
                                    original.setStatus("publish");
                                    original.setTitle(section.getTitle());
                                    original.setContent(section.getContent());
                                    original.setUrl(section.getUrl());
                                    saintsBehaviorSectionRepo.save(original);
                                    // Set the status of section pages
                                    setSectionPagesStatus(publishDto);
                                    // Delete the draft section
                                    saintsBehaviorSectionRepo.delete(section);
                                }, () -> {
                                    throw new NoSuchElementException("Original section not found with ID: " + publishDto.getOriginId());
                                });
                    }
                }, () -> {
                    throw new NoSuchElementException("Section not found with ID: " + publishDto.getId());
                });
    }

    @Transactional
    public void publishSaintsBehaviorSectionPage(SaintsBehaviorSectionPublishDto publishDto) {
        saintsBehaviorSectionPageRepo.findById(publishDto.getId())
                .ifPresentOrElse(page -> {
                    if (publishDto.getOriginId() == null) {
                        // No origin -> publish current page
                        page.setStatus("publish");
                        saintsBehaviorSectionPageRepo.save(page);
                    } else {
                        // Has origin -> update original and remove draft
                        saintsBehaviorSectionPageRepo.findById(publishDto.getOriginId())
                                .ifPresentOrElse(original -> {
                                    // Update original with data from draft
                                    original.setStatus("publish");
                                    original.setTitle(page.getTitle());
                                    original.setContent(page.getContent());
                                    original.setUrl(page.getUrl());
                                    saintsBehaviorSectionPageRepo.save(original);

                                    // Delete the draft page
                                    saintsBehaviorSectionPageRepo.delete(page);
                                }, () -> {
                                    throw new NoSuchElementException("Original page not found with ID: " + publishDto.getOriginId());
                                });
                    }
                }, () -> {
                    throw new NoSuchElementException("Page not found with ID: " + publishDto.getId());
                });
    }

    public List<SaintsBehaviorSectionPageMinDataDto> getSaintsBehaviorSectionPagesBySectionId(Long sectionId, String status) {
        return saintsBehaviorSectionPageRepo.findBySaintsBehaviorSectionIdAndStatus(sectionId, status)
                .stream()
                .map(page -> new SaintsBehaviorSectionPageMinDataDto(
                        page.getId(),
                        page.getTitle(),
                        page.getUrl(),
                        page.getStatus(),
                        page.getAttached()
                ))
                .toList();
    }

    public List<SaintsBehaviorSectionPageMinDataDto> getUnattachedSaintsBehaviorSectionPage() {
        return saintsBehaviorSectionPageRepo.findByAttachedFalse()
                .stream()
                .map(page -> new SaintsBehaviorSectionPageMinDataDto(
                        page.getId(),
                        page.getTitle(),
                        page.getUrl(),
                        page.getStatus(),
                        page.getAttached()
                ))
                .toList();
    }

    public List<SaintsBehaviorSectionPageDto> getDraftSaintsBehaviorSectionPage() {
        return saintsBehaviorSectionPageRepo.findByStatus("draft").stream()
                .map(page -> new SaintsBehaviorSectionPageDto(
                        page.getId(),
                        page.getTitle(),
                        page.getContent(),
                        page.getUrl(),
                        page.getStatus(),
                        page.getOriginId(),
                        page.getSaintsBehaviorSection().getId(),
                        page.getAttached())).toList();
    }
}
