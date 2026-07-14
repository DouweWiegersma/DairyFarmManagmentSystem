package nl.wiegersma.dairyfarm.dtos;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import nl.wiegersma.dairyfarm.enums.Unit;

import java.time.LocalDate;

@Data
public class TreatmentRequestDto {

    @Min(value = 1, message = "min dosage 1")
    @Max(value = 30, message = "max dosage 30")
    private int dosage;

    @Min(value = 1, message = "min duration 1")
    @Max(value = 3, message = "max duration 3")
    private int duration;

    @NotNull(message = "date is required")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;


    @NotNull(message = "cow is required")
    private Long cowId;

    @NotNull(message = "medication is required")
    private Long medicationId;

    @NotNull(message = "medication inventory is required")
    private Long medicationInventoryId;

    @NotNull(message = "disease is required")
    private Long diseaseId;

    @NotNull(message = "time is required")
    private Unit unit;


}
