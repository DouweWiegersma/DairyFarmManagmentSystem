package nl.wiegersma.dairyfarm.mappers;
import nl.wiegersma.dairyfarm.dtos.DiseaseRequestDto;
import nl.wiegersma.dairyfarm.dtos.DiseaseResponseDto;
import nl.wiegersma.dairyfarm.models.Disease;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import java.util.List;


@Mapper(componentModel = "spring")
public interface DiseaseMapper {


    DiseaseResponseDto toDto(Disease clawDisease);

    Disease toEntity(DiseaseRequestDto DiseaseRequestDto);

    void updateDisease(DiseaseRequestDto DiseaseRequestDto, @MappingTarget Disease Disease);

    List<Disease> DiseaseToList(List<DiseaseRequestDto> DiseaseRequestDtoList);

    List<DiseaseResponseDto> DiseaseDtoToList(List<Disease> DiseaseList);
}
