package nl.wiegersma.dairyfarm.dtos;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import nl.wiegersma.dairyfarm.enums.Unit;

@Valid
@Data
public class MedicationInventoryRequestDto {

    @Digits(integer = 7, message = "batchNumber needs 7 numbers", fraction = 0)
    private int batchNumber;

    @NotNull(message = "stock quantity is required")
    @Min(value = 0, message = "stock quantity can't be negative")
    private int stockQuantity;

    @NotNull(message = "medication is required")
    private Long medicationId;

    @NotNull(message = "unit is required")
    private Unit unit;
}
