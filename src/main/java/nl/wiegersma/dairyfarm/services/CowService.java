package nl.wiegersma.dairyfarm.services;
import nl.wiegersma.dairyfarm.dtos.CowRequestDto;
import nl.wiegersma.dairyfarm.dtos.CowResponseDto;
import nl.wiegersma.dairyfarm.exceptions.RecordNotFoundException;
import nl.wiegersma.dairyfarm.mappers.CowMapper;
import nl.wiegersma.dairyfarm.models.Cow;
import nl.wiegersma.dairyfarm.repositories.CowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CowService {

    private final CowRepository cowRepository;
    private final CowMapper cowMapper;

    public CowService(CowRepository cowRepository, CowMapper cowMapper) {
        this.cowRepository = cowRepository;
        this.cowMapper = cowMapper;
    }

    public List<CowResponseDto> getCows(){
        List<Cow> cows = cowRepository.findAll();
        List<CowResponseDto> cowResponseDtoList = cowMapper.toListDto(cows);
        return cowResponseDtoList;
    }

    public CowResponseDto getOneCow(Long id){
        Cow cow = cowRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Cow not found with id:" + id));
        return cowMapper.toDto(cow);
    }

    public CowResponseDto updateCow(Long id, CowRequestDto cowRequestDto){
        Cow cow = cowRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Cow not found with id: " + id));
        Cow updated = cowMapper.updateCow(cowRequestDto, cow);
        cowRepository.save(updated);
        return cowMapper.toDto(updated);
    }

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
