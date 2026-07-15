package nl.wiegersma.dairyfarm.mappers;

import nl.wiegersma.dairyfarm.dtos.CowPhotoResponseDto;
import nl.wiegersma.dairyfarm.models.CowPhoto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CowPhotoMapper {

    CowPhotoResponseDto toDto(CowPhoto cowPhoto);

    CowPhoto toEntity(CowPhotoResponseDto cowPhotoResponseDto);

}
