package nl.wiegersma.dairyfarm.controllers;


import nl.wiegersma.dairyfarm.dtos.TreatmentRequestDto;
import nl.wiegersma.dairyfarm.dtos.TreatmentResponseDto;
import nl.wiegersma.dairyfarm.services.TreatmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Treatments")
public class TreatmentController {

    private TreatmentService treatmentService;

    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreatmentResponseDto> getTreatment(Long id){
        TreatmentResponseDto treatmentResponseDto = treatmentService.getOneTreatment(id);
        return ResponseEntity.status(HttpStatus.OK).body(treatmentResponseDto);
    }

    @GetMapping
    public ResponseEntity<List <TreatmentResponseDto>> getAllTreatments(){
        List<TreatmentResponseDto> treatmentResponseDtoList = treatmentService.getTreatments();
        return ResponseEntity.status(HttpStatus.OK).body(treatmentResponseDtoList);
    }

    @PostMapping
    public ResponseEntity<TreatmentResponseDto> createTreatment(TreatmentRequestDto treatmentRequestDto) {
        TreatmentResponseDto treatmentResponseDto = treatmentService.createTreatment(treatmentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(treatmentResponseDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TreatmentResponseDto> updateTreatment(TreatmentRequestDto treatmentRequestDto, Long id){
        TreatmentResponseDto treatmentResponseDto = treatmentService.updateTreatment(id, treatmentRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(treatmentResponseDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTreatment(Long id){
        treatmentService.deleteTreatment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
