package nl.wiegersma.dairyfarm.controllers;
import jakarta.validation.Valid;
import nl.wiegersma.dairyfarm.dtos.*;
import nl.wiegersma.dairyfarm.models.CowPhoto;
import nl.wiegersma.dairyfarm.services.CowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cows")
public class CowController {

    private final CowService cowService;

    public CowController(CowService cowService) {
        this.cowService = cowService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CowResponseDto> getCow(@PathVariable Long id){
        CowResponseDto cow = cowService.getOneCow(id);
        return ResponseEntity.status(HttpStatus.OK).body(cow);
    }

    @GetMapping("/{cowId}/photo")
    public ResponseEntity<byte[]> getCowPhoto(@PathVariable Long cowId) {
        CowPhoto cowPhoto = cowService.getCowPhoto(cowId);
        if (cowPhoto == null || cowPhoto.getContents() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(cowPhoto.getContentType()))
                    .body(cowPhoto.getContents());
        }
    }

    @GetMapping("{id}/clawtreatments")
    public ResponseEntity<CowAndClawTreatmentResponseDto> getOneCowWithClawTreatments(@PathVariable Long id){
        CowAndClawTreatmentResponseDto cowAndClawTreatmentResponseDto = cowService.getOneCowWithClawTreatments(id);
        return ResponseEntity.status(HttpStatus.OK).body(cowAndClawTreatmentResponseDto);
    }


    @GetMapping("{id}/treatments")
    public ResponseEntity<CowAndTreatmentsResponseDto> getOneCowWithTreatments(@PathVariable Long id){
        CowAndTreatmentsResponseDto cowAndTreatmentsDto = cowService.getOneCowWithTreatments(id);
        return ResponseEntity.status(HttpStatus.OK).body(cowAndTreatmentsDto);
    }


    @GetMapping
    public ResponseEntity<List<CowResponseDto>> getAllCows( @RequestParam (required = false) boolean cowNumber){
        List<CowResponseDto> cowResponseDto = cowService.getCows(cowNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cowResponseDto);
    }


    @PostMapping
    public ResponseEntity<CowResponseDto> createCow(@Valid @RequestBody CowRequestDto cowRequestDto){
        CowResponseDto cowResponseDto = cowService.createCow(cowRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cowResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CowResponseDto> updateCow(@PathVariable Long id, @Valid @RequestBody CowRequestDto cowRequestDto){
        CowResponseDto cowResponseDto = cowService.updateCow(id, cowRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(cowResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCow(@PathVariable Long id){
        cowService.deleteCow(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
