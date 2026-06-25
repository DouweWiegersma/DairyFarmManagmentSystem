package nl.wiegersma.dairyfarm.mappers;

import nl.wiegersma.dairyfarm.dtos.ClawDiseaseRequestDto;
import nl.wiegersma.dairyfarm.dtos.ClawTreatmentRequestDto;
import nl.wiegersma.dairyfarm.dtos.ClawTreatmentResponseDto;
import nl.wiegersma.dairyfarm.models.ClawTreatment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClawTreatmentMapper {

    public ClawTreatment toEntity(ClawTreatmentRequestDto clawTreatmentRequestDto);

    public ClawTreatmentResponseDto toDto(ClawTreatment clawTreatment);

    public void updateClawTreatment(ClawTreatmentRequestDto clawTreatmentRequestDto, @MappingTarget ClawTreatment clawTreatment);

    public List<ClawTreatment> clawTreatmentToEntityList(List<ClawDiseaseRequestDto> clawDiseaseRequestDtoList);

    public List<ClawTreatmentResponseDto> clawTreatmentToDtoList(List<ClawTreatment> clawTreatmentsList);
}
