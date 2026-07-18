package nl.wiegersma.dairyfarm.controllers;

import jakarta.validation.Valid;
import nl.wiegersma.dairyfarm.dtos.DiseaseRequestDto;
import nl.wiegersma.dairyfarm.dtos.DiseaseResponseDto;

import nl.wiegersma.dairyfarm.services.DiseaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/diseases")
public class DiseaseController {


    private final DiseaseService diseaseService;

    public DiseaseController(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiseaseResponseDto> getOneDisease(@PathVariable Long id){
       return ResponseEntity.status(HttpStatus.OK).body(diseaseService.getOneDisease(id));
    }

    @GetMapping
    public ResponseEntity<List<DiseaseResponseDto>> getAllClawDiseases(){
        return ResponseEntity.status(HttpStatus.OK).body(diseaseService.getAllDiseases());
    }

    @PutMapping("{id}")
    public ResponseEntity<DiseaseResponseDto> updateClawDisease(@PathVariable Long id,@Valid @RequestBody DiseaseRequestDto diseaseRequestDto){
        DiseaseResponseDto clawDiseaseResponseDto = diseaseService.updateDisease(diseaseRequestDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(clawDiseaseResponseDto);
    }

    @PostMapping
    public ResponseEntity<DiseaseResponseDto> CreateClawDisease(@Valid @RequestBody DiseaseRequestDto diseaseRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(diseaseService.createDisease(diseaseRequestDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteClawDisease(@PathVariable Long id){
        diseaseService.deleteDisease(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllClawDiseases(){
        diseaseService.deleteAllDiseases();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
