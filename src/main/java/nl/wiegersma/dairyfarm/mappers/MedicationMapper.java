package nl.wiegersma.dairyfarm.mappers;
import nl.wiegersma.dairyfarm.dtos.MedicationRequestDto;
import nl.wiegersma.dairyfarm.dtos.MedicationResponseDto;
import nl.wiegersma.dairyfarm.models.Medication;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicationMapper {

    Medication toEntity(MedicationRequestDto medicationRequestDto);

    MedicationResponseDto toDto(Medication medication);

    Medication updateMedication(MedicationRequestDto medicationRequestDto, @MappingTarget Medication medication);

    List<MedicationResponseDto> toDtoList(List<Medication> medicationList);
}
