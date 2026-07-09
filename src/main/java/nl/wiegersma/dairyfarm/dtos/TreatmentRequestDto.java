package nl.wiegersma.dairyfarm.dtos;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class TreatmentRequestDto {
    private int dosage;
    private int duration;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;
    private String description;
    private Long cowId;
    private Long medicationId;
    private Long medicationInventoryId;
}
