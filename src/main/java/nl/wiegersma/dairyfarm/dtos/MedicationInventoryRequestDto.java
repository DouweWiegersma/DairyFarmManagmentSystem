package nl.wiegersma.dairyfarm.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicationInventoryRequestDto {

    @Size(min = 7, max = 7, message = "BatchNumber needs to have 7 numbers")
    private int batchNumber;

    @NotNull(message = "stock quantity is required")
    @Min(value = 0, message = "stock quantity can't be negative")
    private int stockQuantity;

    @NotNull(message = "medication is required")
    private Long medicationId;
}
