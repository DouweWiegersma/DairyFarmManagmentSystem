package nl.wiegersma.dairyfarm.services;

import nl.wiegersma.dairyfarm.dtos.ClawTreatmentRequestDto;
import nl.wiegersma.dairyfarm.dtos.ClawTreatmentResponseDto;
import nl.wiegersma.dairyfarm.exceptions.RecordNotFoundException;
import nl.wiegersma.dairyfarm.mappers.ClawTreatmentMapper;
import nl.wiegersma.dairyfarm.models.ClawTreatment;
import nl.wiegersma.dairyfarm.repositories.ClawTreatmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClawTreatmentService {

    private final ClawTreatmentRepository clawTreatmentRepository;
    private final ClawTreatmentMapper clawTreatmentMapper;

    public ClawTreatmentService(ClawTreatmentRepository clawTreatmentRepository, ClawTreatmentMapper clawTreatmentMapper) {
        this.clawTreatmentRepository = clawTreatmentRepository;
        this.clawTreatmentMapper = clawTreatmentMapper;
    }

    public ClawTreatmentResponseDto createClawTreatment(ClawTreatmentRequestDto clawTreatmentRequestDto){
        ClawTreatment entity = clawTreatmentMapper.toEntity(clawTreatmentRequestDto);
        clawTreatmentRepository.save(entity);
        return clawTreatmentMapper.toDto(entity);
    }

    public List<ClawTreatmentResponseDto> getAllClawTreatments(){
        List<ClawTreatment> clawTreatmentList = clawTreatmentRepository.findAll();
        List<ClawTreatmentResponseDto> dtoList = clawTreatmentMapper.clawTreatmentToDtoList(clawTreatmentList);
        return dtoList;
    }

    public ClawTreatmentResponseDto getOneClawTreatment(Long id){
        ClawTreatment clawTreatment = clawTreatmentRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Claw Treatment not found with id: " + id));
        return clawTreatmentMapper.toDto(clawTreatment);
    }

    public ClawTreatmentResponseDto updateClawTreatment(Long id, ClawTreatmentRequestDto clawTreatmentRequestDto){
        ClawTreatment clawTreatment1 = clawTreatmentRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Claw Treatment not found with id: " + id));
        clawTreatmentMapper.updateClawTreatment(clawTreatmentRequestDto, clawTreatment1);
        ClawTreatment updated = clawTreatmentRepository.save(clawTreatment1);
        return clawTreatmentMapper.toDto(updated);
    }

    public void deleteClawTreatment(Long id){
        ClawTreatment clawTreatment = clawTreatmentRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Claw Treatment not found with id: " + id));
        clawTreatmentRepository.delete(clawTreatment);
    }

}
