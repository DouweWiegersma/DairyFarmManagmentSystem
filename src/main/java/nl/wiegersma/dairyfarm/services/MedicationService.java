package nl.wiegersma.dairyfarm.services;

import nl.wiegersma.dairyfarm.dtos.MedicationRequestDto;
import nl.wiegersma.dairyfarm.dtos.MedicationResponseDto;
import nl.wiegersma.dairyfarm.exceptions.RecordNotFoundException;
import nl.wiegersma.dairyfarm.exceptions.ResourceInUseException;
import nl.wiegersma.dairyfarm.mappers.MedicationMapper;
import nl.wiegersma.dairyfarm.models.Medication;
import nl.wiegersma.dairyfarm.models.MedicationInventory;
import nl.wiegersma.dairyfarm.repositories.MedicationInventoryRepository;
import nl.wiegersma.dairyfarm.repositories.MedicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationService {

    private final MedicationMapper medicationMapper;
    private final MedicationRepository medicationRepository;
    private final MedicationInventoryRepository medicationInventoryRepository;

    public MedicationService(MedicationMapper medicationMapper, MedicationRepository medicationRepository, MedicationInventoryRepository medicationInventoryRepository) {
        this.medicationMapper = medicationMapper;
        this.medicationRepository = medicationRepository;
        this.medicationInventoryRepository = medicationInventoryRepository;
    }

    public MedicationResponseDto getMedication(Long id){
        Medication medication = medicationRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("medication not found with id: " + id));
        return medicationMapper.toDto(medication);
    }

    public List<MedicationResponseDto> getAllMedications(){
        List<Medication> medication = medicationRepository.findAll();
        return medicationMapper.toDtoList(medication);
    }

    public MedicationResponseDto createMedication(MedicationRequestDto medicationRequestDto){
        Medication medication = medicationMapper.toEntity(medicationRequestDto);
        Medication updated = medicationRepository.save(medication);
        return medicationMapper.toDto(updated);
    }

    public MedicationResponseDto updateMedication(Long id, MedicationRequestDto medicationRequestDto){
        Medication medication = medicationRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("medication not found with id: " + id));
        Medication updated = medicationMapper.updateMedication(medicationRequestDto, medication);
        return medicationMapper.toDto(updated);
    }

    public void deleteMedication(Long id) {
        Medication medication = medicationRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("cant find mediction with id " + id));
            if (!medication.getMedicationInventories().isEmpty()) {
                throw new ResourceInUseException("medication is still in use in the medicationInventories");
            } else {
                medicationRepository.delete(medication);
            }
        }
    }

