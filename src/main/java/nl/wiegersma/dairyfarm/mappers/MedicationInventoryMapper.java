package nl.wiegersma.dairyfarm.mappers;

import nl.wiegersma.dairyfarm.dtos.MedicationInventoryRequestDto;
import nl.wiegersma.dairyfarm.dtos.MedicationInventoryResponseDto;
import nl.wiegersma.dairyfarm.models.MedicationInventory;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

// Je zegt hier je mag gebruik maken van MedicationMapper class als je dit nodig hebt.
@Mapper(componentModel = "spring", uses = MedicationMapper.class)
public interface MedicationInventoryMapper {

    MedicationInventory toEntity(MedicationInventoryRequestDto medicationInventoryRequestDto);


    MedicationInventoryResponseDto toDto(MedicationInventory medicationInventory);

    MedicationInventory updateMedicationInventory(MedicationInventoryRequestDto medicationInventoryRequestDto, @MappingTarget MedicationInventory medicationInventory);

    List<MedicationInventoryResponseDto> toDtoList(List<MedicationInventory> medicationInventoryList);
}
