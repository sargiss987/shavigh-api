package am.shavigh.api.controller;

import am.shavigh.api.dto.saintsbehaviour.SaintsBehaviourDto;
import am.shavigh.api.service.SaintsBehaviorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
