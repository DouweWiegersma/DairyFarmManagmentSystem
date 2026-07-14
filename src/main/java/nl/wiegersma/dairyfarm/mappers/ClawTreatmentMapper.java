package nl.wiegersma.dairyfarm.mappers;
import nl.wiegersma.dairyfarm.dtos.ClawTreatmentRequestDto;
import nl.wiegersma.dairyfarm.dtos.ClawTreatmentResponseDto;
import nl.wiegersma.dairyfarm.models.ClawTreatment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


import java.util.List;


@Mapper(componentModel = "spring", uses = {
        DiseaseMapper.class, CowMapper.class
})
public interface ClawTreatmentMapper {


    ClawTreatment toEntity(ClawTreatmentRequestDto clawTreatmentRequestDto);


    @Mapping(source = "cow.cowNumber", target = "cowNumber")
    @Mapping(source = "disease.name", target = "diseaseName")
    @Mapping(source = "disease.description", target = "diseaseDescription")
    @Mapping(source = "disease.treatment", target = "treatmentSteps")
    ClawTreatmentResponseDto toDto(ClawTreatment clawTreatment);

    void updateClawTreatment(ClawTreatmentRequestDto clawTreatmentRequestDto, @MappingTarget ClawTreatment clawTreatment);

}
