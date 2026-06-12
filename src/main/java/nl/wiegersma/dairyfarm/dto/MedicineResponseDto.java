package nl.wiegersma.dairyfarm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicineResponseDto extends BaseDto {
    private String name;
    private String description;
    private int milkWithholdingPeriod;
    private int meatWithdrawalPeriod;
}
