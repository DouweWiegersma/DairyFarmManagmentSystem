package nl.wiegersma.dairyfarm.services;
import jakarta.transaction.Transactional;
import nl.wiegersma.dairyfarm.dtos.*;
import nl.wiegersma.dairyfarm.exceptions.RecordNotFoundException;
import nl.wiegersma.dairyfarm.mappers.*;
import nl.wiegersma.dairyfarm.models.Cow;
import nl.wiegersma.dairyfarm.models.CowPhoto;
import nl.wiegersma.dairyfarm.repositories.CowRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Service
public class CowService {

    private final CowRepository cowRepository;
    private final CowMapper cowMapper;
    private final ClawTreatmentMapperWithoutCowNumber clawTreatmentMapperWithoutCowNumber;
    private final CowAndTreatmentMapper cowAndTreatmentMapper;


    public CowService(CowRepository cowRepository, CowMapper cowMapper, ClawTreatmentMapperWithoutCowNumber clawTreatmentMapperWithoutCowNumber, CowAndTreatmentMapper cowAndTreatmentMapper) {
        this.cowRepository = cowRepository;
        this.cowMapper = cowMapper;
        this.clawTreatmentMapperWithoutCowNumber = clawTreatmentMapperWithoutCowNumber;
        this.cowAndTreatmentMapper = cowAndTreatmentMapper;
    }

    @Transactional
        public List<CowResponseDto> getCows(boolean cowNumber) {
            List<Cow> cows;
        if(cowNumber) {
            cows = cowRepository.findAllByOrderByCowNumberDesc();
        } else {
            cows = cowRepository.findAllByOrderByCowNumberAsc();
        }
            List<CowResponseDto> cowResponseDtoList = cowMapper.toListDto(cows);
            return cowResponseDtoList;
    }

    @Transactional
    public CowPhoto getCowPhoto(Long cowId) {
        Cow cow = cowRepository.findById(cowId)
                .orElseThrow(() -> new RecordNotFoundException("Koe met ID " + cowId + " niet gevonden"));
       CowPhoto cowPhoto = cow.getCowPhoto();
       return cowPhoto;
    }



    @Transactional
    public CowAndClawTreatmentResponseDto getOneCowWithClawTreatments(@PathVariable Long id){
        Cow cow = cowRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Cow not found with id: " + id));
        CowAndClawTreatmentResponseDto cowAndClawTreatmentResponseDto = new CowAndClawTreatmentResponseDto();
        List<ClawTreatmentResponseDtoWithoutCowNumber> clawTreatmentResponseDto = clawTreatmentMapperWithoutCowNumber.clawTreatmentToDtoList(cow.getClawTreatments());
        cowAndClawTreatmentResponseDto.setClawTreatments(clawTreatmentResponseDto);
        cowAndClawTreatmentResponseDto.setCowNumber(cow.getCowNumber());
        cowAndClawTreatmentResponseDto.setALife(cow.isALife());
        return cowAndClawTreatmentResponseDto;
    }

    @Transactional
    public CowAndTreatmentsResponseDto getOneCowWithTreatments (@PathVariable Long id){
        Cow cow = cowRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Cow not found with id: " + id));
        CowAndTreatmentsResponseDto cowAndTreatmentsDto = new CowAndTreatmentsResponseDto();

        List<TreatmentResponseDtoWithoutCowNumber> treatments = cowAndTreatmentMapper.treatmentToDtoList(cow.getTreatment());

        cowAndTreatmentsDto.setCowNumber(cow.getCowNumber());
        cowAndTreatmentsDto.setALife(cow.isALife());
        cowAndTreatmentsDto.setTreatments(treatments);


        return cowAndTreatmentsDto;
    }



    @Transactional
    public CowResponseDto getOneCow(Long id){
            Cow cow = cowRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Cow not found with id: " + id));
            return  cowMapper.toDto(cow);
    }

    @Transactional
    public CowResponseDto updateCow(Long id, CowRequestDto cowRequestDto){
        Cow cow = cowRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Cow not found with id: " + id));
        cowMapper.updateCow(cowRequestDto, cow);
        cowRepository.save(cow);
        return cowMapper.toDto(cow);
    }

    @Transactional
    public CowResponseDto createCow(CowRequestDto cowRequestDto){
        Cow cow = cowMapper.toEntity(cowRequestDto);
        cowRepository.save(cow);
        return cowMapper.toDto(cow);
    }

    public void deleteCow(Long id){
        Cow cow = cowRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Cow not found with id: " + id));
        cowRepository.delete(cow);
    }
}
