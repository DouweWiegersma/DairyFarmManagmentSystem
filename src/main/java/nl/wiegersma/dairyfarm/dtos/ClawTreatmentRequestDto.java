package nl.wiegersma.dairyfarm.dtos;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import nl.wiegersma.dairyfarm.enums.ClawPosition;
import java.time.LocalDate;


@Getter
@Setter
public class ClawTreatmentRequestDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateClawTreatment;
    private ClawPosition clawPosition;
    private Long diseaseId;
    private Long cowId;
}
