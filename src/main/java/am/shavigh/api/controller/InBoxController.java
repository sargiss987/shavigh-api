package am.shavigh.api.controller;

import am.shavigh.api.dto.CreateInBoxDto;
import am.shavigh.api.model.inbox.InBox;
import am.shavigh.api.service.InBoxService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InBoxController {

    private final InBoxService inBoxService;

    public InBoxController(InBoxService inBoxService) {
        this.inBoxService = inBoxService;
    }

    @PostMapping("/inbox")
    public ResponseEntity<Void> createInBox(@RequestBody CreateInBoxDto createInBoxDto) {
        inBoxService.createInBox(createInBoxDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/inbox")
    public ResponseEntity<List<InBox>> getAllInBoxes() {
        List<InBox> inBoxes = inBoxService.getAllInBoxes();
        return ResponseEntity.ok(inBoxes);
    }

    @DeleteMapping("/inbox/{id}")
    public ResponseEntity<Void> deleteInBox(@PathVariable Long id) {
        inBoxService.deleteInBox(id);
        return ResponseEntity.noContent().build();
    }
}
