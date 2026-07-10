package nl.wiegersma.dairyfarm.services;
import nl.wiegersma.dairyfarm.dtos.TreatmentRequestDto;
import nl.wiegersma.dairyfarm.dtos.TreatmentResponseDto;
import nl.wiegersma.dairyfarm.exceptions.RecordNotFoundException;
import nl.wiegersma.dairyfarm.mappers.TreatmentMapper;
import nl.wiegersma.dairyfarm.models.*;
import nl.wiegersma.dairyfarm.repositories.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final TreatmentMapper treatmentMapper;
    private final CowRepository cowRepository;
    private final MedicationInventoryRepository medicationInventoryRepository;
    private final MedicationRepository medicationRepository;
    private final DiseaseRepository diseaseRepository;

    public TreatmentService(TreatmentRepository treatmentRepository, TreatmentMapper treatmentMapper, CowRepository cowRepository, MedicationInventoryRepository medicationInventoryRepository, MedicationRepository medicationRepository, DiseaseRepository diseaseRepository) {
        this.treatmentRepository = treatmentRepository;
        this.treatmentMapper = treatmentMapper;
        this.cowRepository = cowRepository;
        this.medicationInventoryRepository = medicationInventoryRepository;
        this.medicationRepository = medicationRepository;
        this.diseaseRepository = diseaseRepository;
    }

    public TreatmentResponseDto getOneTreatment(Long id){
        Treatment treatment = treatmentRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Treatment not found with id: " + id));
        TreatmentResponseDto treatmentResponseDto = treatmentMapper.toDto(treatment);
        return treatmentResponseDto;
    }

    public List<TreatmentResponseDto> getTreatments(){
        List<Treatment> treatmentList = treatmentRepository.findAll();
        List<TreatmentResponseDto> treatmentResponseDtoList = treatmentMapper.toDtoList(treatmentList);
        return treatmentResponseDtoList;
    }

    public List<TreatmentResponseDto> createTreatment(TreatmentRequestDto treatmentRequestDto){
        Cow cow = cowRepository.findById(treatmentRequestDto.getCowId()).orElseThrow(() -> new RecordNotFoundException("cow doesn't exist"));
        Medication medication = medicationRepository.findById(treatmentRequestDto.getMedicationId()).orElseThrow(() -> new RecordNotFoundException("this medication doesn't exist"));
        MedicationInventory medicationInventory = medicationInventoryRepository.findById(treatmentRequestDto.getMedicationInventoryId()).orElseThrow(() -> new RecordNotFoundException("this medication Inventory doesn't exist"));
        Disease disease = diseaseRepository.findById(treatmentRequestDto.getDiseaseId()).orElseThrow(() -> new RecordNotFoundException("claw disease doesn't exist wiith id " + treatmentRequestDto.getDiseaseId()));


        List<TreatmentResponseDto> treatments = new ArrayList<>();

        int totalUsage = treatmentRequestDto.getDosage() * treatmentRequestDto.getDuration();
        int newStockQuantity = medicationInventory.getStockQuantity() - totalUsage;
        medicationInventory.setStockQuantity(newStockQuantity);

        for (int i = 0; i < treatmentRequestDto.getDuration(); i++) {
            Treatment treatment = new Treatment();
            LocalDate date = treatmentRequestDto.getDate().plusDays(i);
            treatment.setMedicationInventories(medicationInventory);
            treatment.setMedication(medication);
            treatment.setCow(cow);
            treatment.setDosage(treatmentRequestDto.getDosage());
            treatment.setDuration(treatmentRequestDto.getDuration());
            treatment.setDate(date);
            treatment.setDisease(disease);

            treatmentRepository.save(treatment);
            TreatmentResponseDto treatmentResponseDto = new TreatmentResponseDto();
            treatmentResponseDto.setCowNumber(cow.getCowNumber());
            treatmentResponseDto.setMedicationName(medication.getName());
            treatmentResponseDto.setDosage(treatment.getDosage());
            treatmentResponseDto.setBatchNumber(medicationInventory.getBatchNumber());
            treatmentResponseDto.setDate(date);
            treatmentResponseDto.setDiseaseName(disease.getName());
            treatmentResponseDto.setDescription(disease.getDescription());
            treatments.add(treatmentResponseDto);
        }
        return treatments;
    }

    public TreatmentResponseDto updateTreatment(Long id, TreatmentRequestDto treatmentRequestDto){
        Treatment treatment = treatmentRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Treatment not found with id: " + id));

        Cow cow = cowRepository.findById(treatmentRequestDto.getCowId()).orElseThrow(() -> new RecordNotFoundException("cow not found with id: " + treatmentRequestDto.getCowId()));
            treatment.setCow(cow);

            Medication medication = medicationRepository.findById(treatmentRequestDto.getMedicationId()).orElseThrow(() -> new RecordNotFoundException("medication not found with id " + treatmentRequestDto.getMedicationId()));
            treatment.setMedication(medication);

        MedicationInventory medicationInventory = medicationInventoryRepository.findById(treatmentRequestDto.getMedicationInventoryId()).orElseThrow(() -> new RecordNotFoundException("medication inventory not found with id " + treatmentRequestDto.getMedicationInventoryId()));
        treatment.setMedicationInventories(medicationInventory);

        Treatment updated = treatmentMapper.updateTreatment(treatmentRequestDto, treatment);

        treatmentRepository.save(updated);
        return treatmentMapper.toDto(updated);
    }

    public void deleteTreatment(Long id){
        Treatment treatment = treatmentRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Treatment not found with id: " + id));
        treatmentRepository.delete(treatment);
    }


}
