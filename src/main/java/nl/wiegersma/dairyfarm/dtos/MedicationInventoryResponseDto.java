package nl.wiegersma.dairyfarm.dtos;

import lombok.Getter;
import lombok.Setter;
import nl.wiegersma.dairyfarm.models.Medication;

@Getter
@Setter
public class MedicationInventoryResponseDto extends BaseDto {
    private int batchNumber;
    private int stockQuantity;
    private MedicationResponseDto medication;
}
