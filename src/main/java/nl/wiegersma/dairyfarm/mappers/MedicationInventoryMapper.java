package nl.wiegersma.dairyfarm.mappers;
import nl.wiegersma.dairyfarm.dtos.MedicationInventoryRequestDto;
import nl.wiegersma.dairyfarm.dtos.MedicationInventoryResponseDto;
import nl.wiegersma.dairyfarm.models.MedicationInventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = MedicationMapper.class)
public interface MedicationInventoryMapper {

    @Mapping(source = "unit", target = "unit")
    MedicationInventory toEntity(MedicationInventoryRequestDto medicationInventoryRequestDto);

    @Mapping(source = "unit", target = "unit")
    MedicationInventoryResponseDto toDto(MedicationInventory medicationInventory);

    MedicationInventory updateMedicationInventory(MedicationInventoryRequestDto medicationInventoryRequestDto, @MappingTarget MedicationInventory medicationInventory);

    @Mapping(source = "unit", target = "unit")
    List<MedicationInventoryResponseDto> toDtoList(List<MedicationInventory> medicationInventoryList);
}
