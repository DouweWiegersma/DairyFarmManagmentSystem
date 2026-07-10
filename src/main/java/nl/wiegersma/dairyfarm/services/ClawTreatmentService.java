package nl.wiegersma.dairyfarm.services;
import jakarta.transaction.Transactional;
import nl.wiegersma.dairyfarm.dtos.*;
import nl.wiegersma.dairyfarm.exceptions.RecordNotFoundException;
import nl.wiegersma.dairyfarm.mappers.ClawTreatmentMapper;
import nl.wiegersma.dairyfarm.mappers.DiseaseMapper;
import nl.wiegersma.dairyfarm.models.ClawTreatment;
import nl.wiegersma.dairyfarm.models.Cow;
import nl.wiegersma.dairyfarm.models.Disease;
import nl.wiegersma.dairyfarm.repositories.DiseaseRepository;
import nl.wiegersma.dairyfarm.repositories.ClawTreatmentRepository;
import nl.wiegersma.dairyfarm.repositories.CowRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClawTreatmentService {

    private final ClawTreatmentRepository clawTreatmentRepository;
    private final ClawTreatmentMapper clawTreatmentMapper;
    private final DiseaseRepository diseaseRepository;
    private final CowRepository cowRepository;
    private final DiseaseMapper diseaseMapper;

    public ClawTreatmentService(ClawTreatmentRepository clawTreatmentRepository, ClawTreatmentMapper clawTreatmentMapper, DiseaseRepository diseaseRepository, CowRepository cowRepository, DiseaseMapper diseaseMapper) {
        this.clawTreatmentRepository = clawTreatmentRepository;
        this.clawTreatmentMapper = clawTreatmentMapper;
        this.diseaseRepository = diseaseRepository;
        this.cowRepository = cowRepository;
        this.diseaseMapper = diseaseMapper;
    }

    @Transactional
    public ClawTreatmentResponseDto createClawTreatment(ClawTreatmentRequestDto clawTreatmentRequestDto){
            Disease disease = diseaseRepository.findById(clawTreatmentRequestDto.getDiseaseId()).orElseThrow(() -> new RecordNotFoundException("ClawDisease with id " + clawTreatmentRequestDto.getDiseaseId() + "doesn't exist"));

            ClawTreatment clawTreatment = clawTreatmentMapper.toEntity(clawTreatmentRequestDto);
            clawTreatment.setDisease(disease);

            Cow cow = cowRepository.findById(clawTreatmentRequestDto.getCowId()).orElseThrow(() -> new RecordNotFoundException("Cow with id " + clawTreatmentRequestDto.getCowId() + " doesn't exist"));
            clawTreatment.setCow(cow);
            clawTreatment = clawTreatmentRepository.save(clawTreatment);
            ClawTreatmentResponseDto newClawTreatment = clawTreatmentMapper.toDto(clawTreatment);
            newClawTreatment.setDisease(diseaseMapper.toDto(clawTreatment.getDisease()));

            return newClawTreatment;

    }


    @Transactional
    public List<ClawTreatmentResponseDto> getAllClawTreatments(){
        List<ClawTreatment> clawTreatmentList = clawTreatmentRepository.findAll();
        List<ClawTreatmentResponseDto> dtoList = clawTreatmentMapper.clawTreatmentToDtoList(clawTreatmentList);
        return dtoList;
    }


    @Transactional
    public ClawTreatmentResponseDto getOneClawTreatment(Long clawTreatmentId, boolean diseases){
        if(diseases){
            ClawTreatment clawTreatment = clawTreatmentRepository.findById(clawTreatmentId).orElseThrow(() -> new RecordNotFoundException("ClawTreatment with id " + clawTreatmentId + " doesn't exist"));
            ClawTreatmentResponseDto clawTreatmentResponseDto = clawTreatmentMapper.toDto(clawTreatment);
            Disease disease = clawTreatment.getDisease();
            DiseaseResponseDto diseaseResponseDto = diseaseMapper.toDto(disease);
            clawTreatmentResponseDto.setDisease(diseaseResponseDto);
            return clawTreatmentResponseDto;
        } else {
            ClawTreatment clawTreatment = clawTreatmentRepository.findById(clawTreatmentId).orElseThrow(() -> new RecordNotFoundException("ClawTreatment with id " + clawTreatmentId + " doesn't exist"));
            ClawTreatmentResponseDto clawTreatmentResponseDto = clawTreatmentMapper.toDto(clawTreatment);
            return clawTreatmentResponseDto;
        }
    }


    @Transactional
    public ClawTreatmentResponseDto updateClawTreatment(Long id, ClawTreatmentRequestDto clawTreatmentRequestDto){
        ClawTreatment clawTreatment1 = clawTreatmentRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Claw Treatment not found with id: " + id));
        clawTreatmentMapper.updateClawTreatment(clawTreatmentRequestDto, clawTreatment1);
        if(clawTreatmentRequestDto.getDiseaseId() != null){
            Disease disease = diseaseRepository.findById(clawTreatmentRequestDto.getDiseaseId()).orElseThrow(() -> new RecordNotFoundException("ClawDisease with id " + clawTreatmentRequestDto.getDiseaseId() + "doesn't exist"));
            clawTreatment1.setDisease(disease);
        }
        ClawTreatment updated = clawTreatmentRepository.save(clawTreatment1);
        return clawTreatmentMapper.toDto(updated);
    }

    public void deleteClawTreatment(Long id){
        ClawTreatment clawTreatment = clawTreatmentRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Claw Treatment not found with id: " + id));
        clawTreatment.setDisease(null);
        clawTreatmentRepository.delete(clawTreatment);
    }

}
