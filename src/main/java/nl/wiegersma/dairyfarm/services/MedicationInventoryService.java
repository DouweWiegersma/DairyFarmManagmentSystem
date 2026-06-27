package nl.wiegersma.dairyfarm.services;
import nl.wiegersma.dairyfarm.dtos.MedicationInventoryRequestDto;
import nl.wiegersma.dairyfarm.dtos.MedicationInventoryResponseDto;
import nl.wiegersma.dairyfarm.exceptions.RecordNotFoundException;
import nl.wiegersma.dairyfarm.mappers.MedicationInventoryMapper;
import nl.wiegersma.dairyfarm.models.MedicationInventory;
import nl.wiegersma.dairyfarm.repositories.MedicationInventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationInventoryService {

    private final MedicationInventoryRepository medicationInventoryRepository;
    private final MedicationInventoryMapper medicationInventoryMapper;

    public MedicationInventoryService(MedicationInventoryRepository medicationInventoryRepository, MedicationInventoryMapper medicationInventoryMapper) {
        this.medicationInventoryRepository = medicationInventoryRepository;
        this.medicationInventoryMapper = medicationInventoryMapper;
    }

    public MedicationInventoryResponseDto getMedication(Long id) {
        MedicationInventory medicationInventory = medicationInventoryRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Medication not found with id:" + id));
        MedicationInventoryResponseDto medicationInventoryResponseDtoResponseDto = medicationInventoryMapper.toDto(medicationInventory);
        return medicationInventoryResponseDtoResponseDto;
    }

    public List<MedicationInventoryResponseDto> getAllMedications() {
        List<MedicationInventory> medicationInventoryList = medicationInventoryRepository.findAll();
        List<MedicationInventoryResponseDto> medicationInventoryResponseDtoList = medicationInventoryMapper.toDtoList(medicationInventoryList);
        return medicationInventoryResponseDtoList;
    }

    public MedicationInventoryResponseDto createMedication(MedicationInventoryRequestDto medicationInventoryRequestDto) {
        MedicationInventory medicationInventory = medicationInventoryMapper.toEntity(medicationInventoryRequestDto);
        medicationInventoryRepository.save(medicationInventory);
        return medicationInventoryMapper.toDto(medicationInventory);
    }

    public MedicationInventoryResponseDto updateMedication(Long id, MedicationInventoryRequestDto medicationInventoryRequestDto) {
        MedicationInventory medicationInventory = medicationInventoryRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Medication not found with id: " + id));
        MedicationInventory updated = medicationInventoryMapper.updateMedicationInventory(medicationInventoryRequestDto, medicationInventory);
        medicationInventoryRepository.save(updated);
        return medicationInventoryMapper.toDto(updated);
    }

    public void deleteMedication(Long id) {
        MedicationInventory medicationInventory = medicationInventoryRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Medication not found with id: " + id));
        medicationInventoryRepository.delete(medicationInventory);
    }



}
