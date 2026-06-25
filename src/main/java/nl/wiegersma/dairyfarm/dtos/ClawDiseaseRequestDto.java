package nl.wiegersma.dairyfarm.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClawDiseaseRequestDto {
    private String name;
    private String description;
    private String clawTreatment;
}
