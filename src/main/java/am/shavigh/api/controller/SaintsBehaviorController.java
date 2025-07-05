package am.shavigh.api.controller;

import am.shavigh.api.dto.saintsbehaviour.*;
import am.shavigh.api.service.SaintsBehaviorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SaintsBehaviorController {

    private final SaintsBehaviorService saintsBehaviorService;

    public SaintsBehaviorController(SaintsBehaviorService saintsBehaviorService) {
        this.saintsBehaviorService = saintsBehaviorService;
    }

    @GetMapping("/saints-behavior")
    ResponseEntity<List<SaintsBehaviourDto>> getSaintsBehaviorWithSections(){
        return ResponseEntity.ok(saintsBehaviorService.getSaintsBehaviorWithSections());
    }

    @GetMapping("/saints-behavior/section")
    ResponseEntity<SaintsBehaviourSectionFullDto> getSaintsBehaviorSectionByUrl(@RequestParam("url") String url,
                                                                                @RequestParam(value = "status", defaultValue = "publish") String status){
        return ResponseEntity.ok(saintsBehaviorService.getSaintsBehaviorSectionByUrl(url, status));
    }

    @GetMapping("/saints-behavior/section/pages")
    ResponseEntity<SaintsBehaviorSectionPageDto> getSaintsBehaviorSectionPagesByUrl(@RequestParam("url") String url,
                                                                                    @RequestParam(value = "status", defaultValue = "publish") String status){
        return ResponseEntity.ok(saintsBehaviorService.getSaintsBehaviorSectionPageByUrl(url, status));
    }

    @PostMapping("/saints-behavior/section")
    ResponseEntity<SaintsBehaviourSectionFullDto> getSaintsBehaviorSectionByUrl(@RequestBody CreateSaintsBehaviourSectionDto createSaintsBehaviourSectionDto) {
        return ResponseEntity.ok(saintsBehaviorService.createSaintsBehaviorSection(createSaintsBehaviourSectionDto));
    }

    @PutMapping("/saints-behavior/section/publish")
    public ResponseEntity<Void> publishSaintsBehaviorSection(@RequestBody SaintsBehaviorSectionPublishDto publishDto) {
        saintsBehaviorService.publishSaintsBehaviorSection(publishDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/saints-behavior/section/pages")
    ResponseEntity<SaintsBehaviorSectionPageDto> createSaintsBehaviorSectionPage(@RequestBody CreateBehaviorSectionPageDto createBehaviorSectionPageDto) {
        return ResponseEntity.ok(saintsBehaviorService.createSaintsBehaviorSectionPage(createBehaviorSectionPageDto));
    }

    @PutMapping("/saints-behavior/section/pages/publish")
    public ResponseEntity<Void> publishSaintsBehaviorSectionPage(@RequestBody SaintsBehaviorSectionPublishDto publishDto) {
        saintsBehaviorService.publishSaintsBehaviorSectionPage(publishDto);
        return ResponseEntity.ok().build();
    }
}
