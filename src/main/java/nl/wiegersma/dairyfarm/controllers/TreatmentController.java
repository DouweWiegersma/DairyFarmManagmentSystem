package nl.wiegersma.dairyfarm.controllers;
import jakarta.validation.Valid;
import nl.wiegersma.dairyfarm.dtos.TreatmentRequestDto;
import nl.wiegersma.dairyfarm.dtos.TreatmentResponseDto;
import nl.wiegersma.dairyfarm.services.TreatmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/treatments")
public class TreatmentController {

    private final TreatmentService treatmentService;

    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreatmentResponseDto> getTreatment(@PathVariable Long id){
        TreatmentResponseDto treatmentResponseDto = treatmentService.getOneTreatment(id);
        return ResponseEntity.status(HttpStatus.OK).body(treatmentResponseDto);
    }

    @GetMapping
    public ResponseEntity<List <TreatmentResponseDto>> getAllTreatments(){
        List<TreatmentResponseDto> treatmentResponseDtoList = treatmentService.getTreatments();
        return ResponseEntity.status(HttpStatus.OK).body(treatmentResponseDtoList);
    }

    @PostMapping
    public ResponseEntity<List<TreatmentResponseDto>> createTreatment(@Valid  @RequestBody  TreatmentRequestDto treatmentRequestDto) {
        List<TreatmentResponseDto> treatmentResponseDto = treatmentService.createTreatment(treatmentRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(treatmentResponseDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TreatmentResponseDto> updateTreatment(@Valid @RequestBody TreatmentRequestDto treatmentRequestDto, @PathVariable Long id){
        TreatmentResponseDto treatmentResponseDto = treatmentService.updateTreatment(id, treatmentRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(treatmentResponseDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTreatment(@PathVariable Long id){
        treatmentService.deleteTreatment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
