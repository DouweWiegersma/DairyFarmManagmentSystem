package nl.wiegersma.dairyfarm.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TreatmentResponseDto extends BaseDto {
    private String dosage;
    private int duration;
    private LocalDate date;
    private String description;
}
