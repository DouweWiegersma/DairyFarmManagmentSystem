package nl.wiegersma.dairyfarm.controllers;
import nl.wiegersma.dairyfarm.dtos.CowAndClawTreatmentResponseDto;
import nl.wiegersma.dairyfarm.dtos.CowAndTreatmentsResponseDto;
import nl.wiegersma.dairyfarm.dtos.CowRequestDto;
import nl.wiegersma.dairyfarm.dtos.CowResponseDto;
import nl.wiegersma.dairyfarm.services.CowService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<CowResponseDto> createCow(@RequestBody CowRequestDto cowRequestDto){
        CowResponseDto cowResponseDto = cowService.createCow(cowRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cowResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CowResponseDto> updateCow(@PathVariable Long id, @RequestBody CowRequestDto cowRequestDto){
        CowResponseDto cowResponseDto = cowService.updateCow(id, cowRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(cowResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCow(@PathVariable Long id){
        cowService.deleteCow(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
