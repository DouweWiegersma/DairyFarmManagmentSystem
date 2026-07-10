package nl.wiegersma.dairyfarm.dtos;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class TreatmentRequestDto {

    @Min(1)
    @Max(30)
    private int dosage;


    @NotBlank(message = "Geef aan om welke ziekte het gaat")
    private String disease;

    @Min(1)
    @Max(3)
    private int duration;

    @NotNull(message = "Datum moet worden ingevuld")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;


    @NotNull(message = "selecteer een koe")
    private Long cowId;

    @NotNull(message = "medicatie is niet ingevuld")
    private Long medicationId;

    @NotNull(message = "kies uit de inventory met welk flesje/injector of dergelijke je deze koe wilt behandelen")
    private Long medicationInventoryId;


    private Long diseaseId;
}
