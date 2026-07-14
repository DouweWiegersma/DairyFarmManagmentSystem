package nl.wiegersma.dairyfarm.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import nl.wiegersma.dairyfarm.enums.Time;

@Data
public class MedicationRequestDto {

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "description is required")
    private String description;

    @NotNull(message = "milkWithholdingPeriod is required")
    private Long milkWithholdingPeriod;

    @NotNull(message = "meatWithdrawalPeriod is required")
    private Long meatWithdrawalPeriod;

    @NotNull(message = "time is required")
    private Time time;

}
