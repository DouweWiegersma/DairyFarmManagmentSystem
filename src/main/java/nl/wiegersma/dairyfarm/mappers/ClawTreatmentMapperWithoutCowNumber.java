package nl.wiegersma.dairyfarm.mappers;
import nl.wiegersma.dairyfarm.dtos.ClawTreatmentResponseDtoWithoutCowNumber;
import nl.wiegersma.dairyfarm.models.ClawTreatment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        DiseaseMapper.class, CowMapper.class
})
public interface ClawTreatmentMapperWithoutCowNumber {

    @Mapping(source = "disease.name", target = "diseaseName")
    @Mapping(source = "disease.description", target = "diseaseDescription")
    @Mapping(source = "disease.treatment", target = "treatmentSteps")
    ClawTreatmentResponseDtoWithoutCowNumber toDto(ClawTreatment clawTreatment);

    List<ClawTreatmentResponseDtoWithoutCowNumber> clawTreatmentToDtoList(List<ClawTreatment> clawTreatmentsList);
}
