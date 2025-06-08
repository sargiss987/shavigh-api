package am.shavigh.api.service;

import am.shavigh.api.dto.statics.StaticPageCreateDto;
import am.shavigh.api.dto.statics.StaticPageDto;
import am.shavigh.api.dto.statics.StaticPagePublishDto;
import am.shavigh.api.dto.statics.StaticPageUpdateDto;
import am.shavigh.api.exceptions.ApiException;
import am.shavigh.api.model.statics.StaticPage;
import am.shavigh.api.repo.StaticPagesRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StaticPagesService {

    private final StaticPagesRepo staticPagesRepo;

    public StaticPagesService(StaticPagesRepo staticPagesRepo) {
        this.staticPagesRepo = staticPagesRepo;
    }

    public StaticPageDto findByUniqueName(String uniqueName, String status) {
        var page = staticPagesRepo.findByUniqueNameAndStatus(uniqueName, status);
        if (page == null) {
            throw new ApiException("Static page not found", HttpStatus.NOT_FOUND);
        }
        return new StaticPageDto(page.getId(), page.getUniqueName(), page.getStatus(), page.getContent(), page.getOriginId());
    }

    public StaticPageDto updateStaticPage(StaticPageUpdateDto staticPageUpdateDto) {
        var existingPage = staticPagesRepo.findById(staticPageUpdateDto.getOriginId());
        if (existingPage.isEmpty()) {
            throw new ApiException("Original static page not found", HttpStatus.BAD_REQUEST);
        }

        var staticPage = new StaticPage(staticPageUpdateDto.getId(),staticPageUpdateDto.getUniqueName(),
               "draft" , staticPageUpdateDto.getContent(), staticPageUpdateDto.getOriginId());

        var savedStaticPage = staticPagesRepo.save(staticPage);

        return new StaticPageDto(savedStaticPage.getId(), staticPageUpdateDto.getUniqueName(),
                savedStaticPage.getStatus(), savedStaticPage.getContent(), savedStaticPage.getOriginId());
    }

    @Transactional
    public void publishStaticPage(StaticPagePublishDto staticPagePublishDto) {
        if (staticPagePublishDto.getOriginId() != null){
            var existingOriginPageOpt = staticPagesRepo.findById(staticPagePublishDto.getOriginId());
            var existingDraftPageOpt = staticPagesRepo.findById(staticPagePublishDto.getId());
            if (existingOriginPageOpt.isEmpty()) {
                throw new ApiException("Original static page not found", HttpStatus.BAD_REQUEST);
            }
            if (existingDraftPageOpt.isEmpty()) {
                throw new ApiException("Draft static page not found", HttpStatus.BAD_REQUEST);
            }

            var existingOriginPage = existingOriginPageOpt.get();
            var existingDraftPage = existingDraftPageOpt.get();
            var updatedOriginPage = updateOriginPage(existingOriginPage, existingDraftPage);
            staticPagesRepo.save(updatedOriginPage);
            staticPagesRepo.deleteById(staticPagePublishDto.getId());
        } else {
            var existingDraftPageOpt = staticPagesRepo.findById(staticPagePublishDto.getId());
            if (existingDraftPageOpt.isEmpty()) {
                throw new ApiException("Draft static page not found", HttpStatus.BAD_REQUEST);
            }
            var existingDraftPage = existingDraftPageOpt.get();
            var updatedOriginPage = new StaticPage(existingDraftPage.getId(), existingDraftPage.getUniqueName(),
                    "publish", existingDraftPage.getContent(), null);
            staticPagesRepo.save(updatedOriginPage);
        }
    }

    private StaticPage updateOriginPage(StaticPage existingOriginPage, StaticPage existingDraftPage) {
        existingOriginPage.setStatus("publish");
        existingOriginPage.setContent(existingDraftPage.getContent());
        existingOriginPage.setUniqueName(existingDraftPage.getUniqueName());
        existingOriginPage.setOriginId(null);
        return existingDraftPage;
    }
}
