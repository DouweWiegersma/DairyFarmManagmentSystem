package nl.wiegersma.dairyfarm.services;
import nl.wiegersma.dairyfarm.dtos.*;
import nl.wiegersma.dairyfarm.mappers.ClawTreatmentMapperWithoutCowNumber;
import nl.wiegersma.dairyfarm.mappers.CowAndTreatmentMapper;
import nl.wiegersma.dairyfarm.mappers.CowMapper;
import nl.wiegersma.dairyfarm.models.ClawTreatment;
import nl.wiegersma.dairyfarm.models.Cow;
import nl.wiegersma.dairyfarm.models.Treatment;
import nl.wiegersma.dairyfarm.repositories.CowRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CowServiceTest {

    @Mock
    private CowRepository cowRepository;

    @Mock
    private CowMapper cowMapper;

    @Mock
    private ClawTreatmentMapperWithoutCowNumber clawTreatmentMapperWithoutCowNumber;

    @Mock
    private CowAndTreatmentMapper cowAndTreatmentMapper;

    @InjectMocks
    private CowService cowService;

    @Test
    @DisplayName("Koeien ophalen van laag naar hoog")
    void getCowsWithInAscendingOrder_ShouldReturnListOfCowsInAscOrder(){
//        Arrange
        Cow cow1 = new Cow();
        Cow cow2 = new Cow();
        cow2.setCowNumber(7000L);
        cow1.setCowNumber(7500L);
        List<Cow> cowList = List.of(cow1, cow2);
        List<CowResponseDto> cowResponseDto = List.of(new CowResponseDto());
        when(cowRepository.findAllByOrderByCowNumberAsc()).thenReturn(cowList);
        when(cowMapper.toListDto(cowList)).thenReturn(cowResponseDto);
//        ACT
        List<CowResponseDto> response = cowService.getCows(false);
//        ASSERT
        assertNotNull(response);
    }

    @Test
    @DisplayName("Koeien ophalen van hoog naar laag")
    void getCowsWithInDescendingOrder_ShouldReturnListOfCowsInDescOrder(){
//        ARRANGE
        Cow cow1 = new Cow();
        Cow cow2 = new Cow();
        cow2.setCowNumber(7000L);
        cow1.setCowNumber(7500L);
        List<Cow> cowList = List.of(cow1, cow2);
        List<CowResponseDto> cowResponseDto = List.of(new CowResponseDto());
        when(cowRepository.findAllByOrderByCowNumberDesc()).thenReturn(cowList);
        when(cowMapper.toListDto(cowList)).thenReturn(cowResponseDto);
//        ACT
        List<CowResponseDto> response = cowService.getCows(true);
//        ASSERT
        assertNotNull(response);
    }

    @Test
    @DisplayName("Het ophalen van een foto van een koe uit de repository")
    void getCowPhotoFromRepository_ShouldReturnACowWithoutAPhoto(){
//       ARRANGE
        Cow cow = new Cow();
        cow.setCowNumber(7000L);
        cow.setCowPhoto(null);
        when(cowRepository.findById(1L)).thenReturn(Optional.of(cow));
//        ACT
        cowService.getCowPhoto(1L);
//        ASSERT
        assertEquals(null, cow.getCowPhoto());
    }

    @Test
    @DisplayName("Het ophalen van één koe met de klauw behandelingen")
    void getCowWithTreatments_ShouldReturnACowWithTreatments(){
//        ARRANGE
      Long cowId = 1L;
      Cow cow = new Cow();
      cow.setId(cowId);
      cow.setCowNumber(7500L);
      cow.setALife(true);
      ClawTreatment clawTreatment = new ClawTreatment();
      clawTreatment.setCow(cow);
      clawTreatment.setId(1L);
      List<ClawTreatment> clawTreatmentList = new ArrayList<>();
      clawTreatmentList.add(clawTreatment);
      cow.setClawTreatments(clawTreatmentList);
      List<ClawTreatmentResponseDtoWithoutCowNumber> clawTreatmentResponseDtoWithoutCowNumberList = new ArrayList<>();
        ClawTreatmentResponseDtoWithoutCowNumber clawTreatmentResponseDtoWithoutCowNumber = new ClawTreatmentResponseDtoWithoutCowNumber();
      clawTreatmentResponseDtoWithoutCowNumberList.add(clawTreatmentResponseDtoWithoutCowNumber);
      when(cowRepository.findById(cowId)).thenReturn(Optional.of(cow));
      when(clawTreatmentMapperWithoutCowNumber.clawTreatmentToDtoList(cow.getClawTreatments()
      )).thenReturn(clawTreatmentResponseDtoWithoutCowNumberList);
//      ACT
        CowAndClawTreatmentResponseDto result = cowService.getOneCowWithClawTreatments(cowId);
//        ASSERT
        assertNotNull(result);
    }

    @Test
    @DisplayName("Haal een koe op met behandelingen")
    void getOneCowWithTreatments_ShouldReturnACowWithAListOfTreatments(){
//    ARRANGE
        Treatment treatments = new Treatment();
        treatments.setId(1L);
        List<Treatment> treatmentLists = new ArrayList<>();
        treatmentLists.add(treatments);

        Cow cow = new Cow();
        cow.setCowNumber(7500L);
        cow.setALife(true);
        cow.setId(1L);
        cow.setTreatment(treatmentLists);

        TreatmentResponseDtoWithoutCowNumber treatment = new TreatmentResponseDtoWithoutCowNumber();
        List<TreatmentResponseDtoWithoutCowNumber> listTreatments = new ArrayList<>();
        listTreatments.add(treatment);

        when(cowRepository.findById(1L)).thenReturn(Optional.of(cow));
        when(cowAndTreatmentMapper.treatmentToDtoList(cow.getTreatment())).thenReturn(listTreatments);


//        ACT
        CowAndTreatmentsResponseDto result = cowService.getOneCowWithTreatments(cow.getId());

//        ASSERT
        assertEquals(listTreatments, result.getTreatments());
        assertEquals(cow.getCowNumber() , result.getCowNumber());
    }

    @Test
    @DisplayName("Het ophalen van één koe")
    void getOneCow_ShouldReturnOneCow(){
//        ARRANGE
        Cow cow = new Cow();
        cow.setCowNumber(7500L);
        cow.setId(1L);

        CowResponseDto cowResponseDto = new CowResponseDto();
        cowResponseDto.setId(1L);
        cowResponseDto.setCowNumber(7500L);

        when(cowRepository.findById(cow.getId())).thenReturn(Optional.of(cow));
        when(cowMapper.toDto(cow)).thenReturn(cowResponseDto);
//        ACT
        CowResponseDto result = cowService.getOneCow(cow.getId());
//        ASSERT
        assertEquals(cowResponseDto, result);
    }

    @Test
    @DisplayName("Het updaten van één koe")
    void updateCow_ShouldReturnAUpdatedCow(){
//        ARRANGE
        Cow cow = new Cow();
        cow.setId(1L);
        cow.setCowNumber(7500L);

        CowRequestDto cowRequestDto = new CowRequestDto();
        cowRequestDto.setCowNumber(7400L);

        CowResponseDto updatedCow = new CowResponseDto();
        updatedCow.setCowNumber(cow.getCowNumber());
        updatedCow.setId(cow.getId());

        when(cowRepository.findById(cow.getId()))
                .thenReturn(Optional.of(cow));

        when(cowRepository.save(cow))
                .thenReturn(cow);

        doNothing().when(cowMapper).updateCow(cowRequestDto, cow);

        when(cowMapper.toDto(cow))
                .thenReturn(updatedCow);
//        ACT
        CowResponseDto result = cowService.updateCow(1L, cowRequestDto);

//        ASSERT
        assertEquals(updatedCow.getCowNumber(), result.getCowNumber());


    }

    @Test
    @DisplayName("Maak een koe aan")
    void createACow_ShouldReturnACowResponseDto(){
//        ARRANGE
        Cow cow = new Cow();
        cow.setId(1L);
        cow.setCowNumber(7500L);

        CowRequestDto cowRequestDto = new CowRequestDto();
        cowRequestDto.setCowNumber(7500L);

        CowResponseDto cowResponseDto = new CowResponseDto();
        cowResponseDto.setId(1L);
        cowResponseDto.setCowNumber(7500L);

        when(cowMapper.toEntity(cowRequestDto)).thenReturn(cow);
        when(cowRepository.save(cow)).thenReturn(cow);
        when(cowMapper.toDto(cow)).thenReturn(cowResponseDto);
//        ACT
        CowResponseDto result = cowService.createCow(cowRequestDto);

//        ASSERT
        assertEquals(cowResponseDto, result);

    }

    @Test
    @DisplayName("Verwijderen van een koe")
    void deleteCow_ShouldReturnNothing(){
//        ARRANGE
        Cow cow = new Cow();
        cow.setId(1L);
        cow.setCowNumber(7500L);

        when(cowRepository.findById(cow.getId())).thenReturn(Optional.of(cow));
        doNothing().when(cowRepository).delete(cow);

//        ACT
        cowService.deleteCow(1L);

//        ASSERT
        verify(cowRepository).delete(cow);
    }

}