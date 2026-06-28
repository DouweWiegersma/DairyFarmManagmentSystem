package nl.wiegersma.dairyfarm.controllers;


import nl.wiegersma.dairyfarm.dtos.MedicationRequestDto;
import nl.wiegersma.dairyfarm.dtos.MedicationResponseDto;
import nl.wiegersma.dairyfarm.services.MedicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medications")
public class MedicationController {

    private final MedicationService medicationService;

    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicationResponseDto> getMedication(Long id){
        MedicationResponseDto medicationResponseDto = medicationService.getMedication(id);
        return ResponseEntity.status(HttpStatus.OK).body(medicationResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<MedicationResponseDto>> getAllMedications(){
        List<MedicationResponseDto> medicationResponseDtoList = medicationService.getAllMedications();
        return ResponseEntity.status(HttpStatus.OK).body(medicationResponseDtoList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicationResponseDto> updateMedication(MedicationRequestDto medicationRequestDto, Long id){
        MedicationResponseDto medicationResponseDto = medicationService.updateMedication(id, medicationRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(medicationResponseDto);
    }

    @PostMapping
    public ResponseEntity<MedicationResponseDto> createMedication(MedicationRequestDto medicationRequestDto){
        MedicationResponseDto medicationResponseDto = medicationService.createMedication(medicationRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicationResponseDto);
    }

    @DeleteMapping("/{id")
    public ResponseEntity<Void> deleteMedication(Long id){
        medicationService.deleteMedication(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
