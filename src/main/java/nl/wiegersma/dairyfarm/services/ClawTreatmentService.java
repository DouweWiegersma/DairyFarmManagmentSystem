package nl.wiegersma.dairyfarm.services;
import jakarta.transaction.Transactional;
import nl.wiegersma.dairyfarm.dtos.*;
import nl.wiegersma.dairyfarm.exceptions.RecordNotFoundException;
import nl.wiegersma.dairyfarm.mappers.ClawDiseaseMapper;
import nl.wiegersma.dairyfarm.mappers.ClawTreatmentMapper;
import nl.wiegersma.dairyfarm.mappers.CowMapper;
import nl.wiegersma.dairyfarm.models.ClawDisease;
import nl.wiegersma.dairyfarm.models.ClawTreatment;
import nl.wiegersma.dairyfarm.models.Cow;
import nl.wiegersma.dairyfarm.repositories.ClawDiseaseRepository;
import nl.wiegersma.dairyfarm.repositories.ClawTreatmentRepository;
import nl.wiegersma.dairyfarm.repositories.CowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClawTreatmentService {

    private final ClawTreatmentRepository clawTreatmentRepository;
    private final ClawTreatmentMapper clawTreatmentMapper;
    private final ClawDiseaseRepository clawDiseaseRepository;
    private final CowRepository cowRepository;
    private final ClawDiseaseMapper clawDiseaseMapper;


    public ClawTreatmentService(ClawTreatmentRepository clawTreatmentRepository, ClawTreatmentMapper clawTreatmentMapper, ClawDiseaseRepository clawDiseaseRepository, CowRepository cowRepository, ClawDiseaseMapper clawDiseaseMapper) {
        this.clawTreatmentRepository = clawTreatmentRepository;
        this.clawTreatmentMapper = clawTreatmentMapper;
        this.clawDiseaseRepository = clawDiseaseRepository;
        this.cowRepository = cowRepository;
        this.clawDiseaseMapper = clawDiseaseMapper;
    }

    @Transactional
    public ClawTreatmentResponseDto createClawTreatment(ClawTreatmentRequestDto clawTreatmentRequestDto){

            ClawDisease clawDisease = clawDiseaseRepository.findById(clawTreatmentRequestDto.getClawDiseaseId()).orElseThrow(() -> new RecordNotFoundException("ClawDisease with id " + clawTreatmentRequestDto.getClawDiseaseId() + "doesn't exist"));
            ClawTreatment clawTreatment = clawTreatmentMapper.toEntity(clawTreatmentRequestDto);
            clawTreatment.setClawDisease(clawDisease);
            Cow cow = cowRepository.findById(clawTreatmentRequestDto.getCowId()).orElseThrow(() -> new RecordNotFoundException("Cow with id " + clawTreatmentRequestDto.getCowId() + " doesn't exist"));
            clawTreatment.setCow(cow);
            clawTreatment = clawTreatmentRepository.save(clawTreatment);
            return clawTreatmentMapper.toDto(clawTreatment);

    }


    @Transactional
    public List<ClawTreatmentResponseDto> getAllClawTreatments(){
        List<ClawTreatment> clawTreatmentList = clawTreatmentRepository.findAll();
        List<ClawTreatmentResponseDto> dtoList = clawTreatmentMapper.clawTreatmentToDtoList(clawTreatmentList);
        return dtoList;
    }


    @Transactional
    public ClawTreatmentResponseDto getOneClawTreatment(Long clawTreatmentId, boolean clawDiseases){
        if(clawDiseases){
            ClawTreatment clawTreatment = clawTreatmentRepository.findById(clawTreatmentId).orElseThrow(() -> new RecordNotFoundException("ClawTreatment with id " + clawTreatmentId +" doesn't exist"));
            ClawTreatmentResponseDto clawTreatmentResponseDto = clawTreatmentMapper.toDto(clawTreatment);
            ClawDisease clawDisease = clawTreatment.getClawDisease();
            ClawDiseaseResponseDto clawDiseaseResponseDto = clawDiseaseMapper.toDto(clawDisease);
            clawTreatmentResponseDto.setClawDisease(clawDiseaseResponseDto);
            return clawTreatmentResponseDto;
        } else{
            ClawTreatment clawTreatment = clawTreatmentRepository.findById(clawTreatmentId).orElseThrow(() -> new RecordNotFoundException("ClawTreatment with id " + clawTreatmentId +" doesn't exist"));
            ClawTreatmentResponseDto clawTreatmentResponseDto = clawTreatmentMapper.toDto(clawTreatment);
            return clawTreatmentResponseDto;
        }
    }


    @Transactional
    public ClawTreatmentResponseDto updateClawTreatment(Long id, ClawTreatmentRequestDto clawTreatmentRequestDto){
        ClawTreatment clawTreatment1 = clawTreatmentRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Claw Treatment not found with id: " + id));
        clawTreatmentMapper.updateClawTreatment(clawTreatmentRequestDto, clawTreatment1);
        if(clawTreatmentRequestDto.getClawDiseaseId() != null){
            ClawDisease clawDisease = clawDiseaseRepository.findById(clawTreatmentRequestDto.getClawDiseaseId()).orElseThrow(() -> new RecordNotFoundException("ClawDisease with id " + clawTreatmentRequestDto.getClawDiseaseId() + "doesn't exist"));
            clawTreatment1.setClawDisease(clawDisease);
        }
        ClawTreatment updated = clawTreatmentRepository.save(clawTreatment1);
        return clawTreatmentMapper.toDto(updated);
    }

    public void deleteClawTreatment(Long id){
        ClawTreatment clawTreatment = clawTreatmentRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Claw Treatment not found with id: " + id));
        clawTreatment.setClawDisease(null);
        clawTreatmentRepository.delete(clawTreatment);
    }

}
