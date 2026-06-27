package nl.wiegersma.dairyfarm.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicationResponseDto extends BaseDto {
    private String name;
    private String description;
    private int milkWithholdingPeriod;
    private int meatWithdrawalPeriod;
}
