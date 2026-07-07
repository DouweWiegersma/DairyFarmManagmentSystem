package nl.wiegersma.dairyfarm.mappers;
import nl.wiegersma.dairyfarm.dtos.CowRequestDto;
import nl.wiegersma.dairyfarm.dtos.CowResponseDto;
import nl.wiegersma.dairyfarm.models.Cow;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import java.util.List;

@Mapper(componentModel = "spring", uses = ClawTreatmentMapper.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CowMapper {

    Cow toEntity(CowRequestDto cowRequestDto);

    CowResponseDto toDto(Cow cow);

    void updateCow(CowRequestDto cowRequestDto, @MappingTarget Cow cow);

    List<CowResponseDto> toListDto(List<Cow> cowList);
}
