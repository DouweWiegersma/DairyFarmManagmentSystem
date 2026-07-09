package nl.wiegersma.dairyfarm.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;



@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreatmentResponseDto extends BaseDto {
    private int dosage;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;
    private String description;
    private String medicationName;
    private Long cowNumber;
    private int batchNumber;



}
