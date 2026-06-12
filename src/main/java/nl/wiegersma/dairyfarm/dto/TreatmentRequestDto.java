package nl.wiegersma.dairyfarm.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TreatmentRequestDto {
    private String dosage;
    private int duration;
    private LocalDate date;
    private String description;
}
