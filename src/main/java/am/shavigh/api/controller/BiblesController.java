package am.shavigh.api.controller;

import am.shavigh.api.repo.BiblesRepo;
import am.shavigh.api.service.BiblesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BiblesController {

    private final BiblesService biblesService;

    public BiblesController(BiblesService biblesService) {
        this.biblesService = biblesService;
    }


    @GetMapping("/bibles")
    public ResponseEntity<?> getBibles() {
        return ResponseEntity.ok(biblesService.getBibleDtoList());
    }
}
