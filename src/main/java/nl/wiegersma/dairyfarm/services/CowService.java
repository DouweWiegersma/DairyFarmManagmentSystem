package nl.wiegersma.dairyfarm.services;
import jakarta.transaction.Transactional;
import nl.wiegersma.dairyfarm.dtos.ClawTreatmentResponseDto;
import nl.wiegersma.dairyfarm.dtos.CowAndClawTreatmentResponseDto;
import nl.wiegersma.dairyfarm.dtos.CowRequestDto;
import nl.wiegersma.dairyfarm.dtos.CowResponseDto;
import nl.wiegersma.dairyfarm.exceptions.RecordNotFoundException;
import nl.wiegersma.dairyfarm.mappers.ClawTreatmentMapper;
import nl.wiegersma.dairyfarm.mappers.CowMapper;
import nl.wiegersma.dairyfarm.models.ClawTreatment;
import nl.wiegersma.dairyfarm.models.Cow;
import nl.wiegersma.dairyfarm.repositories.ClawTreatmentRepository;
import nl.wiegersma.dairyfarm.repositories.CowRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CowService {

    private final CowRepository cowRepository;
    private final CowMapper cowMapper;
    private final ClawTreatmentMapper clawTreatmentMapper;
    private final ClawTreatmentRepository clawTreatmentRepository;

    public CowService(CowRepository cowRepository, CowMapper cowMapper, ClawTreatmentMapper clawTreatmentMapper, ClawTreatmentRepository clawTreatmentRepository) {
        this.cowRepository = cowRepository;
        this.cowMapper = cowMapper;
        this.clawTreatmentMapper = clawTreatmentMapper;
        this.clawTreatmentRepository = clawTreatmentRepository;
    }

    @Transactional
        public List<CowResponseDto> getCows(boolean clawTreatments, boolean cowNumber) {
            List<Cow> cows;
        if(cowNumber) {
            cows = cowRepository.findAllByOrderByCowNumberDesc();
        } else {
            cows = cowRepository.findAllByOrderByCowNumberAsc();
        }

            List<CowResponseDto> cowResponseDtoList = cowMapper.toListDto(cows);

            if(clawTreatments) {
                for (int i = 0; i < cows.size(); i++) {
                    cowResponseDtoList.get(i).setClawTreatment(
                            clawTreatmentMapper.clawTreatmentToDtoList(cows.get(i).getClawTreatments()));
                }

            }
            return cowResponseDtoList;
    }

    @Transactional
    public CowAndClawTreatmentResponseDto getOneCow(Long id, boolean clawTreatments){
        if(clawTreatments){
            Cow cow = cowRepository.findById(id)
                    .orElseThrow(() -> new RecordNotFoundException("Cow not found with id: " + id));
            List<ClawTreatment> clawTreatmentList = cow.getClawTreatments();
            CowResponseDto cowResponseDto = cowMapper.toDto(cow);
            List<ClawTreatmentResponseDto> clawTreatmentResponseDtoList = clawTreatmentMapper.clawTreatmentToDtoList(clawTreatmentList);
            CowAndClawTreatmentResponseDto cowAndTreatment = new CowAndClawTreatmentResponseDto();
            cowAndTreatment.setCow(cowResponseDto);
            cowAndTreatment.setClawTreatment(clawTreatmentResponseDtoList);
            return cowAndTreatment;
        } else{
            Cow cow = cowRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Cow not found with id:" + id));
            CowResponseDto cowResponseDto = cowMapper.toDto(cow);
            CowAndClawTreatmentResponseDto cowAndClawTreatmentResponseDto = new CowAndClawTreatmentResponseDto();
            cowAndClawTreatmentResponseDto.setCow(cowResponseDto);
            return cowAndClawTreatmentResponseDto;

        }
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
