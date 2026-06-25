package nl.wiegersma.dairyfarm.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClawDiseaseRequestDto {
    private String name;
    private String description;

    @NotNull
    private List<String> clawTreatment;
}
