package nl.wiegersma.dairyfarm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClawDiseaseRequestDto {
    private String name;
    private String description;
    private String clawTreatment;
}
