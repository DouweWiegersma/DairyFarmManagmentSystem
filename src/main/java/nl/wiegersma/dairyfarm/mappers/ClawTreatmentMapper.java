package nl.wiegersma.dairyfarm.mappers;
import nl.wiegersma.dairyfarm.dtos.ClawTreatmentRequestDto;
import nl.wiegersma.dairyfarm.dtos.ClawTreatmentResponseDto;
import nl.wiegersma.dairyfarm.models.ClawTreatment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


import java.util.List;


@Mapper(componentModel = "spring", uses = ClawDiseaseMapper.class)
public interface ClawTreatmentMapper {


    ClawTreatment toEntity(ClawTreatmentRequestDto clawTreatmentRequestDto);

    ClawTreatmentResponseDto toDto(ClawTreatment clawTreatment);

    void updateClawTreatment(ClawTreatmentRequestDto clawTreatmentRequestDto, @MappingTarget ClawTreatment clawTreatment);


    List<ClawTreatment> clawTreatmentToEntityList(List<ClawTreatmentRequestDto> clawTreatmentRequestDtoList);

    List<ClawTreatmentResponseDto> clawTreatmentToDtoList(List<ClawTreatment> clawTreatmentsList);
}
