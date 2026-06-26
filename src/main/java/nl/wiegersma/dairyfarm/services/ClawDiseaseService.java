package nl.wiegersma.dairyfarm.services;

import nl.wiegersma.dairyfarm.dtos.ClawDiseaseRequestDto;
import nl.wiegersma.dairyfarm.dtos.ClawDiseaseResponseDto;
import nl.wiegersma.dairyfarm.exceptions.RecordNotFoundException;
import nl.wiegersma.dairyfarm.mappers.ClawDiseaseMapper;
import nl.wiegersma.dairyfarm.models.ClawDisease;
import nl.wiegersma.dairyfarm.repositories.ClawDiseaseRepository;
import nl.wiegersma.dairyfarm.util.ClawTreatmentHashMap;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClawDiseaseService {

    private final ClawDiseaseRepository clawDiseaseRepository;
    private final ClawDiseaseMapper clawDiseaseMapper;

    public ClawDiseaseService(ClawDiseaseRepository clawDiseaseRepository, ClawDiseaseMapper clawDiseaseMapper) {
        this.clawDiseaseRepository = clawDiseaseRepository;
        this.clawDiseaseMapper = clawDiseaseMapper;
    }


    public ClawDiseaseResponseDto createClawDisease(ClawDiseaseRequestDto clawDiseaseRequestDto){
        ClawDisease entity = clawDiseaseMapper.toEntity(clawDiseaseRequestDto);
        clawDiseaseRepository.save(entity);
        return clawDiseaseMapper.toDto(entity);
    }

    public ClawDiseaseResponseDto updateClawDisease(ClawDiseaseRequestDto clawDiseaseRequestDto, Long id){
        ClawDisease entity = clawDiseaseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Claw disease not found with id: " + id));
        clawDiseaseMapper.updateClawDisease(clawDiseaseRequestDto, entity);
        clawDiseaseRepository.save(entity);
        return clawDiseaseMapper.toDto(entity);
    }

    public List<ClawDiseaseResponseDto> getAllClawDiseases(){
        List<ClawDisease> diseaseList = clawDiseaseRepository.findAll().stream().toList();
        List<ClawDiseaseResponseDto> clawDiseaseResponseDto = clawDiseaseMapper.clawDiseaseDtoToList(diseaseList);
        return  clawDiseaseResponseDto;
    }

    public ClawDiseaseResponseDto getOneClawDisease(Long id){
        ClawDisease clawDisease = clawDiseaseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Claw disease not found with id: " + id ));
        ClawDiseaseResponseDto clawDiseaseResponseDto = clawDiseaseMapper.toDto(clawDisease);
        HashMap<String, String> treatmentMap = ClawTreatmentHashMap.addStep(clawDisease.getClawTreatment());
        clawDiseaseResponseDto.setClawTreatment(treatmentMap);
        return clawDiseaseResponseDto;
    }

    public void deleteAllClawDiseases(){
        clawDiseaseRepository.deleteAll();
    }

    public void deleteClawDisease(Long id){
        ClawDisease clawDisease = clawDiseaseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Claw disease not found with id: " + id));
        clawDiseaseRepository.delete(clawDisease);
    }






}
