package nl.wiegersma.dairyfarm.dtos;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import nl.wiegersma.dairyfarm.enums.ClawPosition;
import java.time.LocalDate;


@Getter
@Setter
public class ClawTreatmentRequestDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @PastOrPresent(message = "date is in the future")
    private LocalDate dateClawTreatment;

    @NotNull(message = "claw position is required")
    private ClawPosition clawPosition;

    @NotNull(message = "disease is required")
    private Long diseaseId;

    @NotNull(message = "cow is required")
    private Long cowId;
}
