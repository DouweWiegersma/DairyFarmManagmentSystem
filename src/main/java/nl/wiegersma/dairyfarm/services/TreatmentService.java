package nl.wiegersma.dairyfarm.services;
import nl.wiegersma.dairyfarm.dtos.TreatmentRequestDto;
import nl.wiegersma.dairyfarm.dtos.TreatmentResponseDto;
import nl.wiegersma.dairyfarm.exceptions.RecordNotFoundException;
import nl.wiegersma.dairyfarm.mappers.TreatmentMapper;
import nl.wiegersma.dairyfarm.models.Treatment;
import nl.wiegersma.dairyfarm.repositories.TreatmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final TreatmentMapper treatmentMapper;

    public TreatmentService(TreatmentRepository treatmentRepository, TreatmentMapper treatmentMapper) {
        this.treatmentRepository = treatmentRepository;
        this.treatmentMapper = treatmentMapper;
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

    public TreatmentResponseDto createTreatment(TreatmentRequestDto treatmentRequestDto){
        Treatment treatment = treatmentMapper.toEntity(treatmentRequestDto);
        treatmentRepository.save(treatment);
        return treatmentMapper.toDto(treatment);
    }

    public TreatmentResponseDto updateTreatment(Long id, TreatmentRequestDto treatmentRequestDto){
        Treatment treatment = treatmentRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Treatment not found with id: " + id));
        Treatment updated = treatmentMapper.updateTreatment(treatmentRequestDto, treatment);
        return treatmentMapper.toDto(updated);
    }

    public void deleteTreatment(Long id){
        Treatment treatment = treatmentRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Treatment not found with id: " + id));
        treatmentRepository.delete(treatment);
    }


}
