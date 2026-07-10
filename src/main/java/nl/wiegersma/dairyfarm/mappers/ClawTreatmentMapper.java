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

    @Mapping(target = "disease", ignore = true)
    ClawTreatmentResponseDto toDto(ClawTreatment clawTreatment);

    void updateClawTreatment(ClawTreatmentRequestDto clawTreatmentRequestDto, @MappingTarget ClawTreatment clawTreatment);


    List<ClawTreatment> clawTreatmentToEntityList(List<ClawTreatmentRequestDto> clawTreatmentRequestDtoList);

    List<ClawTreatmentResponseDto> clawTreatmentToDtoList(List<ClawTreatment> clawTreatmentsList);
}
