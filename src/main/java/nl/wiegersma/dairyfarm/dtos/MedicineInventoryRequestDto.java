package nl.wiegersma.dairyfarm.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicineInventoryRequestDto {
    private int BatchNumber;
    private int StockQuantity;
}
