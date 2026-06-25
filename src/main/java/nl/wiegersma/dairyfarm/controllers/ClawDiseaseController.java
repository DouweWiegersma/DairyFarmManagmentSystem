package nl.wiegersma.dairyfarm.controllers;

import nl.wiegersma.dairyfarm.dtos.ClawDiseaseRequestDto;
import nl.wiegersma.dairyfarm.dtos.ClawDiseaseResponseDto;
import nl.wiegersma.dairyfarm.services.ClawDiseaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clawdisease")
public class ClawDiseaseController {


    private final ClawDiseaseService clawDiseaseService;

    public ClawDiseaseController(ClawDiseaseService clawDiseaseService) {
        this.clawDiseaseService = clawDiseaseService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClawDiseaseResponseDto> getOneClawDisease(@PathVariable Long id){
       return ResponseEntity.status(HttpStatus.OK).body(clawDiseaseService.getOneClawDisease(id));
    }

    @GetMapping
    public ResponseEntity<List<ClawDiseaseResponseDto>> getAllClawDiseases(){
        return ResponseEntity.status(HttpStatus.OK).body(clawDiseaseService.getAllClawDiseases());
    }

    @PutMapping("{id}")
    public ResponseEntity<ClawDiseaseResponseDto> updateClawDisease(Long id, @RequestBody ClawDiseaseRequestDto clawDiseaseRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(clawDiseaseService.updateClawDisease(clawDiseaseRequestDto, id));
    }

    @PostMapping
    public ResponseEntity<ClawDiseaseResponseDto> CreateClawDisease(@RequestBody ClawDiseaseRequestDto clawDiseaseRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(clawDiseaseService.createClawDisease(clawDiseaseRequestDto));
    }

    @DeleteMapping("{id")
    public ResponseEntity<Void> deleteClawDisease(Long id){
        clawDiseaseService.deleteClawDisease(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllClawDiseases(){
        clawDiseaseService.deleteAllClawDiseases();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
