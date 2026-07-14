package nl.wiegersma.dairyfarm.dtos;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CowAndTreatmentsResponseDto {


    private boolean aLife;
    private Long cowNumber;
    private List<TreatmentResponseDtoWithoutCowNumber> treatments;
}
