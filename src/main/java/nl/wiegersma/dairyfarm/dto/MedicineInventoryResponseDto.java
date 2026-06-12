package nl.wiegersma.dairyfarm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicineInventoryResponseDto extends BaseDto {
    private int batchNumber;
    private int stockQuantity;
}
