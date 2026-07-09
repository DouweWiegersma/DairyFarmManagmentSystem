package nl.wiegersma.dairyfarm.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class CowResponseDto extends BaseDto{
    private Long cowNumber;
    private boolean aLife;

    private List<ClawTreatmentResponseDto> clawTreatment;

}
