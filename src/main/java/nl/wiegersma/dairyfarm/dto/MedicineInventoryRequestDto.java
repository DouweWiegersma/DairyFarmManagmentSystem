package nl.wiegersma.dairyfarm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicineInventoryRequestDto {
    private int BatchNumber;
    private int StockQuantity;
}
