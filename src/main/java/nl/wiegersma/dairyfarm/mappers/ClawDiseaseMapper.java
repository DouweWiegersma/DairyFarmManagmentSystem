package nl.wiegersma.dairyfarm.mappers;

import nl.wiegersma.dairyfarm.dtos.ClawDiseaseRequestDto;
import nl.wiegersma.dairyfarm.dtos.ClawDiseaseResponseDto;
import nl.wiegersma.dairyfarm.models.ClawDisease;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClawDiseaseMapper {

    public ClawDiseaseResponseDto toDto(ClawDisease clawDisease);

    public ClawDisease toEntity(ClawDiseaseRequestDto clawDiseaseRequestDto);

    public void updateClawDisease(ClawDiseaseRequestDto clawDiseaseRequestDto, @MappingTarget ClawDisease clawDisease);

    public List<ClawDisease> clawDiseaseToList(List<ClawDiseaseRequestDto> clawDiseaseRequestDtoList);

    public List<ClawDiseaseResponseDto> clawDiseaseDtoToList(List<ClawDisease> clawDiseaseList);
}
