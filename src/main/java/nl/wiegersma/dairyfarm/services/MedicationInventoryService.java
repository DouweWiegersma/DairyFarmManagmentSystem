package nl.wiegersma.dairyfarm.services;
import nl.wiegersma.dairyfarm.dtos.MedicationInventoryRequestDto;
import nl.wiegersma.dairyfarm.dtos.MedicationInventoryResponseDto;
import nl.wiegersma.dairyfarm.exceptions.RecordNotFoundException;
import nl.wiegersma.dairyfarm.mappers.MedicationInventoryMapper;
import nl.wiegersma.dairyfarm.models.Medication;
import nl.wiegersma.dairyfarm.models.MedicationInventory;
import nl.wiegersma.dairyfarm.repositories.MedicationInventoryRepository;
import nl.wiegersma.dairyfarm.repositories.MedicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationInventoryService {

    private final MedicationInventoryRepository medicationInventoryRepository;
    private final MedicationInventoryMapper medicationInventoryMapper;
    private final MedicationRepository medicationRepository;

    public MedicationInventoryService(MedicationInventoryRepository medicationInventoryRepository, MedicationInventoryMapper medicationInventoryMapper, MedicationRepository medicationRepository) {
        this.medicationInventoryRepository = medicationInventoryRepository;
        this.medicationInventoryMapper = medicationInventoryMapper;
        this.medicationRepository = medicationRepository;
    }

    public MedicationInventoryResponseDto getMedicationInventory(Long id) {
        MedicationInventory medicationInventory = medicationInventoryRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Medication not found with id:" + id));
        MedicationInventoryResponseDto medicationInventoryResponseDtoResponseDto = medicationInventoryMapper.toDto(medicationInventory);
        return medicationInventoryResponseDtoResponseDto;
    }



    public List<MedicationInventoryResponseDto> getAllMedicationsInventory() {
        List<MedicationInventory> medicationInventoryList = medicationInventoryRepository.findAll();
        List<MedicationInventoryResponseDto> medicationInventoryResponseDtoList = medicationInventoryMapper.toDtoList(medicationInventoryList);
        return medicationInventoryResponseDtoList;
    }

    public MedicationInventoryResponseDto createMedicationInventory(MedicationInventoryRequestDto medicationInventoryRequestDto) {
        MedicationInventory medicationInventory = medicationInventoryMapper.toEntity(medicationInventoryRequestDto);
        Medication medication = medicationRepository.findById(medicationInventoryRequestDto.getMedicationId()).orElseThrow(() -> new RecordNotFoundException("cant find this id!"));
        medicationInventory.setMedication(medication);
        medicationInventoryRepository.save(medicationInventory);
        return medicationInventoryMapper.toDto(medicationInventory);
    }

    public MedicationInventoryResponseDto updateMedicationInventory(Long id, MedicationInventoryRequestDto medicationInventoryRequestDto) {
        MedicationInventory medicationInventory = medicationInventoryRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Medication not found with id: " + id));
        MedicationInventory updated = medicationInventoryMapper.updateMedicationInventory(medicationInventoryRequestDto, medicationInventory);
        medicationInventoryRepository.save(updated);
        return medicationInventoryMapper.toDto(updated);
    }

    public void deleteMedicationInventory(Long id) {
        MedicationInventory medicationInventory = medicationInventoryRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Medication not found with id: " + id));
        medicationInventoryRepository.delete(medicationInventory);
    }



}
