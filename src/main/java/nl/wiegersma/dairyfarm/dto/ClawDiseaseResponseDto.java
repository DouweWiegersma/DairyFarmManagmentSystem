package nl.wiegersma.dairyfarm.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClawDiseaseResponseDto extends BaseDto{
    private String name;
    private String description;
    private String clawTreatment;
}
