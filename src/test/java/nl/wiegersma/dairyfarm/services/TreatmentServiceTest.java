package nl.wiegersma.dairyfarm.services;

import nl.wiegersma.dairyfarm.dtos.TreatmentRequestDto;
import nl.wiegersma.dairyfarm.dtos.TreatmentResponseDto;
import nl.wiegersma.dairyfarm.enums.Unit;
import nl.wiegersma.dairyfarm.exceptions.ValueToHighException;
import nl.wiegersma.dairyfarm.mappers.TreatmentMapper;
import nl.wiegersma.dairyfarm.models.*;
import nl.wiegersma.dairyfarm.repositories.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TreatmentServiceTest {

    @Mock
    TreatmentRepository treatmentRepository;

    @Mock
    TreatmentMapper treatmentMapper;

    @Mock
    CowRepository cowRepository;

    @Mock
    MedicationRepository medicationRepository;

    @Mock
    MedicationInventoryRepository medicationInventoryRepository;

    @Mock
    DiseaseRepository diseaseRepository;

    @InjectMocks
    TreatmentService treatmentService;

    @Test
    @DisplayName("Het ophalen van één treatment")
    void getOneTreatment_ShouldReturnOneTreatmentResponseDto() {
//        ARRANGE
        Treatment treatment = new Treatment();
        treatment.setId(1L);

        TreatmentResponseDto treatmentResponseDto = new TreatmentResponseDto();
        treatmentResponseDto.setId(1L);
        when(treatmentRepository.findById(1L)).thenReturn(Optional.of(treatment));
        when(treatmentMapper.toDto(treatment)).thenReturn(treatmentResponseDto);
//        ACT
        TreatmentResponseDto result = treatmentService.getOneTreatment(treatment.getId());
//        ASSERT
        assertNotNull(result);
    }

    @Test
    @DisplayName("Het ophalen van een lijst van treatments")
    void getAllTreatments_ShouldReturnAListOfTreatmentResponseDtos() {
        Treatment treatment = new Treatment();
        treatment.setId(1L);
        List<Treatment> treatmentList = new ArrayList<>();
        treatmentList.add(treatment);

        TreatmentResponseDto treatmentResponseDto = new TreatmentResponseDto();
        treatmentResponseDto.setId(1L);
        List<TreatmentResponseDto> treatmentResponseDtoList = new ArrayList<>();
        treatmentResponseDtoList.add(treatmentResponseDto);
        when(treatmentRepository.findAll()).thenReturn(treatmentList);
        when(treatmentMapper.toDtoList(treatmentList)).thenReturn(treatmentResponseDtoList);

//        ACT
        List<TreatmentResponseDto> result = treatmentService.getTreatments();
//      ASSERT
        assertEquals(treatmentResponseDtoList, result);
    }

    @Test
    @DisplayName("Het maken van een treatment")
    void createTreatment_ShouldRetrunATreatmentResponseDto() {

        Cow cow = new Cow();
        cow.setId(1L);
        cow.setCowNumber(7500L);

        when(cowRepository.findById(1L))
                .thenReturn(Optional.of(cow));

        Medication medication = new Medication();
        medication.setId(1L);
        medication.setName("Test medicatie");

        when(medicationRepository.findById(1L))
                .thenReturn(Optional.of(medication));


        MedicationInventory medicationInventory = new MedicationInventory();
        medicationInventory.setId(1L);
        medicationInventory.setStockQuantity(100);
        medicationInventory.setBatchNumber(10000);

        when(medicationInventoryRepository.findById(1L))
                .thenReturn(Optional.of(medicationInventory));


        Disease disease = new Disease();
        disease.setId(1L);
        disease.setName("Mortellaro");
        disease.setDescription("klauw ontsteking");

        when(diseaseRepository.findById(1L))
                .thenReturn(Optional.of(disease));


        TreatmentRequestDto treatmentRequestDto = new TreatmentRequestDto();
        treatmentRequestDto.setCowId(1L);
        treatmentRequestDto.setMedicationId(1L);
        treatmentRequestDto.setMedicationInventoryId(1L);
        treatmentRequestDto.setDiseaseId(1L);
        treatmentRequestDto.setDosage(30);
        treatmentRequestDto.setDuration(1);
        treatmentRequestDto.setDate(LocalDate.of(1995, 3, 3));
        treatmentRequestDto.setUnit(Unit.CC);


//      ACT

        List<TreatmentResponseDto> result =
                treatmentService.createTreatment(treatmentRequestDto);


//      ASSERT

        assertEquals(1, result.size());
        assertEquals(70, medicationInventory.getStockQuantity());

    }

    @Test
    @DisplayName("Maak een behandeling waarbij er niet genoeg medicatie op voorraad is")
    void notEnoughTreatmentInStock_ShouldReturnException() {
        Cow cow = new Cow();
        cow.setId(1L);
        cow.setCowNumber(7500L);

        when(cowRepository.findById(1L))
                .thenReturn(Optional.of(cow));

        Medication medication = new Medication();
        medication.setId(1L);
        medication.setName("Test medicatie");

        when(medicationRepository.findById(1L))
                .thenReturn(Optional.of(medication));


        MedicationInventory medicationInventory = new MedicationInventory();
        medicationInventory.setId(1L);
        medicationInventory.setStockQuantity(100);
        medicationInventory.setBatchNumber(10000);

        when(medicationInventoryRepository.findById(1L))
                .thenReturn(Optional.of(medicationInventory));


        Disease disease = new Disease();
        disease.setId(1L);
        disease.setName("Mortellaro");
        disease.setDescription("klauw ontsteking");

        when(diseaseRepository.findById(1L))
                .thenReturn(Optional.of(disease));


        TreatmentRequestDto treatmentRequestDto = new TreatmentRequestDto();
        treatmentRequestDto.setCowId(1L);
        treatmentRequestDto.setMedicationId(1L);
        treatmentRequestDto.setMedicationInventoryId(1L);
        treatmentRequestDto.setDiseaseId(1L);
        treatmentRequestDto.setDosage(300);
        treatmentRequestDto.setDuration(1);
        treatmentRequestDto.setDate(LocalDate.of(1995, 3, 3));
        treatmentRequestDto.setUnit(Unit.CC);


//        ASSERT / ACT
        assertThrows(ValueToHighException.class, () -> treatmentService.createTreatment(treatmentRequestDto));

    }

    @Test
    @DisplayName("Het updaten van een behandeling")
    void updateTreatment_ShouldReturnAUpdatedTreatmentResponseDto() {
        Treatment treatment = new Treatment();
        treatment.setId(1L);
        when(treatmentRepository.findById(treatment.getId())).thenReturn(Optional.of(treatment));

        Cow cow = new Cow();
        cow.setId(1L);
        when(cowRepository.findById(cow.getId())).thenReturn(Optional.of(cow));

        Medication medication = new Medication();
        medication.setId(1L);
        when(medicationRepository.findById(medication.getId())).thenReturn(Optional.of(medication));

        MedicationInventory medicationInventory = new MedicationInventory();
        medicationInventory.setId(1L);
        when(medicationInventoryRepository.findById(medicationInventory.getId())).thenReturn(Optional.of(medicationInventory));

        TreatmentRequestDto treatmentRequestDto = new TreatmentRequestDto();
        treatmentRequestDto.setCowId(1L);
        treatmentRequestDto.setMedicationId(1L);
        treatmentRequestDto.setMedicationInventoryId(1L);
        treatmentRequestDto.setDosage(30);

        TreatmentResponseDto treatmentResponseDto = new TreatmentResponseDto();
        treatmentResponseDto.setId(1L);

        when(treatmentMapper.updateTreatment(treatmentRequestDto, treatment)).thenReturn(treatment);
        when(treatmentRepository.save(treatment)).thenReturn(treatment);
        when(treatmentMapper.toDto(treatment)).thenReturn(treatmentResponseDto);

//      ACT
        TreatmentResponseDto result = treatmentService.updateTreatment(1L, treatmentRequestDto);

//        Assert
        assertNotNull(result);
        assertEquals(result, treatmentResponseDto);
        verify(cowRepository).findById(1L);
        verify(medicationRepository).findById(1L);
        verify(medicationInventoryRepository).findById(1L);
    }

    @Test
    @DisplayName("Het verwijderen van een behandeling")
    void deleteTreatment_ShouldReturnNothing(){
        Treatment treatment = new Treatment();
        treatment.setId(1L);
        when(treatmentRepository.findById(1L)).thenReturn(Optional.of(treatment));
        doNothing().when(treatmentRepository).delete(treatment);

        treatmentService.deleteTreatment(1L);

        verify(treatmentRepository).delete(treatment);
        verify(treatmentRepository).findById(1L);
    }

}