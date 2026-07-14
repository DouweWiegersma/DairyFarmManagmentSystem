package nl.wiegersma.dairyfarm.dtos;

import lombok.Getter;
import lombok.Setter;
import nl.wiegersma.dairyfarm.enums.Unit;

@Getter
@Setter
public class MedicationInventoryResponseDto extends BaseDto {
    private int batchNumber;
    private int stockQuantity;
    private MedicationResponseDto medication;
    private Unit unit;

    public String getStock(){
        return stockQuantity + " " + unit.toString().toLowerCase();
    }
}
