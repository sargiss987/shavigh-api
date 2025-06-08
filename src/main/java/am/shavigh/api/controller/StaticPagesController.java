package am.shavigh.api.controller;

import am.shavigh.api.dto.statics.StaticPageCreateDto;
import am.shavigh.api.dto.statics.StaticPageDto;
import am.shavigh.api.dto.statics.StaticPagePublishDto;
import am.shavigh.api.dto.statics.StaticPageUpdateDto;
import am.shavigh.api.service.StaticPagesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StaticPagesController {

    private final StaticPagesService staticPagesService;

    public StaticPagesController(StaticPagesService staticPagesService) {
        this.staticPagesService = staticPagesService;
    }

    @GetMapping("/static-pages/{uniqueName}")
    public ResponseEntity<StaticPageDto> getStaticPageByUniqueName(@PathVariable("uniqueName") String uniqueName,
                                                                   @RequestParam(value = "status", defaultValue = "publish") String status) {
        return ResponseEntity.ok(staticPagesService.findByUniqueName(uniqueName, status));
    }

    @PutMapping("/static-pages")
    public ResponseEntity<StaticPageDto> updateStaticPage(
            @RequestBody StaticPageUpdateDto staticPageUpdateDto) {
        return ResponseEntity.ok(staticPagesService.updateStaticPage(staticPageUpdateDto));
    }

    @PutMapping("/static-pages/publish")
    public ResponseEntity<Void> updateStaticPageById(
            @RequestBody StaticPagePublishDto staticPagePublishDto) {
        staticPagesService.publishStaticPage(staticPagePublishDto);
        return ResponseEntity.ok().build();
    }


}
