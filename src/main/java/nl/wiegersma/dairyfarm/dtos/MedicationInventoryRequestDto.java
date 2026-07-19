package nl.wiegersma.dairyfarm.dtos;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import nl.wiegersma.dairyfarm.enums.Unit;

@Valid
@Data
public class MedicationInventoryRequestDto {

    @Min(value = 1000000, message = "Het nummer moet minimaal 7 cijfers bevatten")
    @Max(value = 9999999, message = "Het nummer mag maximaal 7 cijfers bevatten")
    private int batchNumber;

    @NotNull(message = "stock quantity is required")
    @Min(value = 0, message = "stock quantity can't be negative")
    private int stockQuantity;

    @NotNull(message = "medication is required")
    private Long medicationId;

    @NotNull(message = "unit is required")
    private Unit unit;
}
