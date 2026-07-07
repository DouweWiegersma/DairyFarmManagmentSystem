package nl.wiegersma.dairyfarm.controllers;
import nl.wiegersma.dairyfarm.dtos.MedicationInventoryRequestDto;
import nl.wiegersma.dairyfarm.dtos.MedicationInventoryResponseDto;
import nl.wiegersma.dairyfarm.services.MedicationInventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/medicationinventories")
public class MedicationInventoryController {

    private final MedicationInventoryService medicationInventoryService;

    public MedicationInventoryController(MedicationInventoryService medicationInventoryService) {
        this.medicationInventoryService = medicationInventoryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicationInventoryResponseDto> getOneMedicationInventory(@PathVariable Long id){
        MedicationInventoryResponseDto medicationInventoryResponseDto = medicationInventoryService.getMedicationInventory(id);
        return ResponseEntity.status(HttpStatus.OK).body(medicationInventoryResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<MedicationInventoryResponseDto>> getMedicationInventories(){
        List<MedicationInventoryResponseDto> medicationInventoryList = medicationInventoryService.getAllMedicationsInventory();
        return ResponseEntity.status(HttpStatus.OK).body(medicationInventoryList);
    }

    @PostMapping
    public ResponseEntity<MedicationInventoryResponseDto> createMedicationInventory(@RequestBody MedicationInventoryRequestDto medicationInventoryRequestDto){
       MedicationInventoryResponseDto medicationInventoryResponseDto = medicationInventoryService.createMedicationInventory(medicationInventoryRequestDto);
       return ResponseEntity.status(HttpStatus.CREATED).body(medicationInventoryResponseDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<MedicationInventoryResponseDto> updateMedicationInventory(@PathVariable Long id, @RequestBody MedicationInventoryRequestDto medicationInventoryRequestDto){
        MedicationInventoryResponseDto medicationInventoryResponseDto = medicationInventoryService.updateMedicationInventory(id, medicationInventoryRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(medicationInventoryResponseDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMedicationInventory(@PathVariable Long id){
        medicationInventoryService.deleteMedicationInventory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
