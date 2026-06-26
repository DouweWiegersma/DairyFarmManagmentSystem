package nl.wiegersma.dairyfarm.mappers;
import nl.wiegersma.dairyfarm.dtos.ClawDiseaseRequestDto;
import nl.wiegersma.dairyfarm.dtos.ClawDiseaseResponseDto;
import nl.wiegersma.dairyfarm.models.ClawDisease;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.util.List;


@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClawDiseaseMapper {

    @Mapping(target = "clawTreatment", ignore = true)
    ClawDiseaseResponseDto toDto(ClawDisease clawDisease);

    ClawDisease toEntity(ClawDiseaseRequestDto clawDiseaseRequestDto);

    void updateClawDisease(ClawDiseaseRequestDto clawDiseaseRequestDto, @MappingTarget ClawDisease clawDisease);

    List<ClawDisease> clawDiseaseToList(List<ClawDiseaseRequestDto> clawDiseaseRequestDtoList);

    List<ClawDiseaseResponseDto> clawDiseaseDtoToList(List<ClawDisease> clawDiseaseList);
}
