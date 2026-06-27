package nl.wiegersma.dairyfarm.mappers;

import nl.wiegersma.dairyfarm.dtos.TreatmentRequestDto;
import nl.wiegersma.dairyfarm.dtos.TreatmentResponseDto;
import nl.wiegersma.dairyfarm.models.Treatment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TreatmentMapper {

    Treatment toEntity(TreatmentRequestDto treatmentRequestDto);

    TreatmentResponseDto toDto(Treatment treatment);

    Treatment updateTreatment(TreatmentRequestDto treatmentRequestDto, @MappingTarget Treatment treatment);

    List<TreatmentResponseDto> toDtoList(List<Treatment> treatmentList);
}
