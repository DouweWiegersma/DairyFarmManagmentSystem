package nl.wiegersma.dairyfarm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicineRequestDto {
    private String name;
    private String description;
    private int milkWithholdingPeriod;
    private int meatWithdrawalPeriod;
}
