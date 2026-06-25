package nl.wiegersma.dairyfarm.controllers;

import nl.wiegersma.dairyfarm.dtos.ClawDiseaseResponseDto;
import nl.wiegersma.dairyfarm.services.ClawDiseaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/clawdisease")
public class ClawDiseaseController {


    private final ClawDiseaseService clawDiseaseService;

    public ClawDiseaseController(ClawDiseaseService clawDiseaseService) {
        this.clawDiseaseService = clawDiseaseService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClawDiseaseResponseDto> getOneClawDisease(@PathVariable Long id){
        ClawclawDiseaseService.getOneClawDisease(id);
    }
}
