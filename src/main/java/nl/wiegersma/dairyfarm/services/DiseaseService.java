package nl.wiegersma.dairyfarm.services;
import nl.wiegersma.dairyfarm.dtos.DiseaseRequestDto;
import nl.wiegersma.dairyfarm.dtos.DiseaseResponseDto;
import nl.wiegersma.dairyfarm.exceptions.RecordNotFoundException;
import nl.wiegersma.dairyfarm.mappers.DiseaseMapper;
import nl.wiegersma.dairyfarm.models.ClawTreatment;
import nl.wiegersma.dairyfarm.models.Disease;
import nl.wiegersma.dairyfarm.repositories.DiseaseRepository;
import nl.wiegersma.dairyfarm.repositories.ClawTreatmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DiseaseService {

    private final DiseaseRepository DiseaseRepository;
    private final DiseaseMapper diseaseMapper;
    private final ClawTreatmentRepository clawTreatmentRepository;

    public DiseaseService(DiseaseRepository diseaseRepository, DiseaseMapper diseaseMapper, ClawTreatmentRepository clawTreatmentRepository) {
        DiseaseRepository = diseaseRepository;
        this.diseaseMapper = diseaseMapper;
        this.clawTreatmentRepository = clawTreatmentRepository;
    }

    public DiseaseResponseDto createDisease(DiseaseRequestDto DiseaseRequestDto){
        Disease entity = diseaseMapper.toEntity(DiseaseRequestDto);
        DiseaseRepository.save(entity);
        return diseaseMapper.toDto(entity);
    }

    public DiseaseResponseDto updateDisease(DiseaseRequestDto DiseaseRequestDto, Long id){
        Disease entity = DiseaseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Claw disease not found with id: " + id));
        diseaseMapper.updateDisease(DiseaseRequestDto, entity);
        DiseaseRepository.save(entity);
        return diseaseMapper.toDto(entity);
    }

    public List<DiseaseResponseDto> getAllDiseases(){
        List<Disease> diseaseList = DiseaseRepository.findAll().stream().toList();
        return diseaseMapper.DiseaseDtoToList(diseaseList);
    }

    public DiseaseResponseDto getOneDisease(Long id){
        Disease disease = DiseaseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Claw disease not found with id: " + id ));
        DiseaseResponseDto DiseaseResponseDto = diseaseMapper.toDto(disease);
        return DiseaseResponseDto;
    }

    public void deleteAllDiseases(){
        DiseaseRepository.deleteAll();
    }

    public void deleteDisease(Long id){
        Disease disease = DiseaseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Claw disease not found with id: " + id));
        List<ClawTreatment> clawTreatments = clawTreatmentRepository.findByDiseaseId(id);

        for (ClawTreatment clawTreatment : clawTreatments) {
            clawTreatment.setDisease(null);
        }
        clawTreatmentRepository.saveAll(clawTreatments);
        DiseaseRepository.delete(disease);

    }






}
