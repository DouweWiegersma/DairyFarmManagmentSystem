package nl.wiegersma.dairyfarm.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicationInventoryResponseDto extends BaseDto {
    private int batchNumber;
    private int stockQuantity;
}
