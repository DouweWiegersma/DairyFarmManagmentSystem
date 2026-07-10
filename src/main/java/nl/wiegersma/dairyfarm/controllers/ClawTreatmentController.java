package nl.wiegersma.dairyfarm.controllers;

import nl.wiegersma.dairyfarm.dtos.ClawTreatmentRequestDto;
import nl.wiegersma.dairyfarm.dtos.ClawTreatmentResponseDto;
import nl.wiegersma.dairyfarm.services.ClawTreatmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clawtreatments")
public class ClawTreatmentController {

    private final ClawTreatmentService clawTreatmentService;

    public ClawTreatmentController(ClawTreatmentService clawTreatmentService) {
        this.clawTreatmentService = clawTreatmentService;
    }

    @PostMapping
    public ResponseEntity<ClawTreatmentResponseDto> createClawTreatment(@RequestBody ClawTreatmentRequestDto clawTreatmentRequestDto){
        ClawTreatmentResponseDto clawTreatmentResponseDto = clawTreatmentService.createClawTreatment(clawTreatmentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clawTreatmentResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<ClawTreatmentResponseDto>> getAllClawTreatments(){
        List<ClawTreatmentResponseDto> clawTreatmentResponseDto = clawTreatmentService.getAllClawTreatments();
        return ResponseEntity.status(HttpStatus.OK).body(clawTreatmentResponseDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<ClawTreatmentResponseDto> getOneClawTreatment(@PathVariable Long id, @RequestParam boolean diseases){
        ClawTreatmentResponseDto clawTreatmentResponseDto = clawTreatmentService.getOneClawTreatment(id, diseases);
        return ResponseEntity.status(HttpStatus.OK).body(clawTreatmentResponseDto);
    }

    @PutMapping("{id}/clawDiseases")
    public ResponseEntity<ClawTreatmentResponseDto> updateClawTreatment(@PathVariable Long id, @RequestBody ClawTreatmentRequestDto clawTreatmentRequestDto){
        ClawTreatmentResponseDto clawTreatmentResponseDto = clawTreatmentService.updateClawTreatment(id, clawTreatmentRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(clawTreatmentResponseDto);
    }

    @DeleteMapping("{id}/clawdiseases")
    public ResponseEntity<Void> deleteClawTreatment(@PathVariable Long id){
        clawTreatmentService.deleteClawTreatment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
