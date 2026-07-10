package nl.wiegersma.dairyfarm.mappers;

import nl.wiegersma.dairyfarm.dtos.TreatmentRequestDto;
import nl.wiegersma.dairyfarm.dtos.TreatmentResponseDto;

import nl.wiegersma.dairyfarm.models.Treatment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        MedicationInventoryMapper.class, MedicationMapper.class, CowMapper.class, DiseaseMapper.class
})
public interface TreatmentMapper {

    @Mapping(target = "disease", ignore = true)
    Treatment toEntity(TreatmentRequestDto treatmentRequestDto);

    @Mapping(source = "cow.cowNumber", target = "cowNumber")
    @Mapping(source = "medication.name", target = "medicationName")
    @Mapping(source = "medicationInventories.batchNumber", target = "batchNumber")
    TreatmentResponseDto toDto(Treatment treatment);


    @Mapping(target = "disease", ignore = true)
    @Mapping(target = "cow", ignore = true)
    @Mapping(target = "medication", ignore = true)
    @Mapping(target = "medicationInventories", ignore = true)
    Treatment updateTreatment(TreatmentRequestDto treatmentRequestDto, @MappingTarget Treatment treatment);

    List<Treatment> toEntity(List<TreatmentRequestDto> treatmentRequestDtos);

    @Mapping(source = "cow.cowNumber", target = "cowNumber")
    @Mapping(source = "medication.name", target = "medicationName")
    @Mapping(source = "medicationInventories.batchNumber", target = "batchNumber")
    List<TreatmentResponseDto> toDtoList(List<Treatment> treatmentList);
}
