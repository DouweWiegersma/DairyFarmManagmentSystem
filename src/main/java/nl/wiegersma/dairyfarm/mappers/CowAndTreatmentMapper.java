package nl.wiegersma.dairyfarm.mappers;
import nl.wiegersma.dairyfarm.dtos.TreatmentResponseDtoWithoutCowNumber;
import nl.wiegersma.dairyfarm.models.Treatment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;
@Mapper(componentModel = "spring", uses = {
        DiseaseMapper.class, CowMapper.class, MedicationMapper.class
})
public interface CowAndTreatmentMapper {

    @Mapping(source = "medication.name", target = "medicationName")
    @Mapping(source = "medicationInventories.batchNumber", target = "batchNumber")
    @Mapping(source = "disease.name", target = "diseaseName")
    @Mapping(source = "disease.description", target = "description")
    TreatmentResponseDtoWithoutCowNumber toDto(Treatment treatment);

    @Mapping(source = "medication.name", target = "medicationName")
    @Mapping(source = "medicationInventories.batchNumber", target = "batchNumber")
    List<TreatmentResponseDtoWithoutCowNumber> treatmentToDtoList(List<Treatment> treatmentList);
}


