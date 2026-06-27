package nl.wiegersma.dairyfarm.mappers;
import nl.wiegersma.dairyfarm.dtos.ClawTreatmentRequestDto;
import nl.wiegersma.dairyfarm.dtos.ClawTreatmentResponseDto;
import nl.wiegersma.dairyfarm.models.ClawTreatment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


import java.util.List;


@Mapper(componentModel = "spring")
public interface ClawTreatmentMapper {

    ClawTreatment toEntity(ClawTreatmentRequestDto clawTreatmentRequestDto);

    ClawTreatmentResponseDto toDto(ClawTreatment clawTreatment);

    @org.mapstruct.BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateClawTreatment(ClawTreatmentRequestDto clawTreatmentRequestDto, @MappingTarget ClawTreatment clawTreatment);

//    List<ClawTreatment> clawTreatmentToEntityList(List<ClawTreatmentRequestDto> clawTreatmentRequestDtos);

    List<ClawTreatmentResponseDto> clawTreatmentToDtoList(List<ClawTreatment> clawTreatmentsList);
}
