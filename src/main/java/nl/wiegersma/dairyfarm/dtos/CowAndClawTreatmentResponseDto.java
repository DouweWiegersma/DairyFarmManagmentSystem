package nl.wiegersma.dairyfarm.dtos;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CowAndClawTreatmentResponseDto {

    private CowResponseDto cow;
    private List<ClawTreatmentResponseDto> clawTreatment;
}
