package nl.wiegersma.dairyfarm.dtos;

import lombok.Getter;
import lombok.Setter;
import nl.wiegersma.dairyfarm.enums.ClawPosition;

import java.time.LocalDate;

@Getter
@Setter
public class ClawTreatmentRequestDto {
    private LocalDate dateHoofTreatment;
    private ClawPosition clawPosition;
}
