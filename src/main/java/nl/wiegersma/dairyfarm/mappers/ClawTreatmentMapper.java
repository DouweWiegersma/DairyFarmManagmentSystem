package nl.wiegersma.dairyfarm.mappers;

import nl.wiegersma.dairyfarm.dtos.ClawDiseaseRequestDto;
import nl.wiegersma.dairyfarm.dtos.ClawTreatmentRequestDto;
import nl.wiegersma.dairyfarm.dtos.ClawTreatmentResponseDto;
import nl.wiegersma.dairyfarm.models.ClawTreatment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClawTreatmentMapper {

    ClawTreatment toEntity(ClawTreatmentRequestDto clawTreatmentRequestDto);

    ClawTreatmentResponseDto toDto(ClawTreatment clawTreatment);

    void updateClawTreatment(ClawTreatmentRequestDto clawTreatmentRequestDto, @MappingTarget ClawTreatment clawTreatment);

    List<ClawTreatment> clawTreatmentToEntityList(List<ClawDiseaseRequestDto> clawDiseaseRequestDtoList);

    List<ClawTreatmentResponseDto> clawTreatmentToDtoList(List<ClawTreatment> clawTreatmentsList);
}
