package nl.wiegersma.dairyfarm.dtos;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class ClawTreatmentResponseDtoWithoutCowNumber {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateClawTreatment;
    private String clawPosition;
    private String diseaseName;
    private String diseaseDescription;
    private List<String> treatmentSteps;
}
