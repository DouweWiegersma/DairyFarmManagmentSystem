package nl.wiegersma.dairyfarm.models;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import nl.wiegersma.dairyfarm.enums.ClawPosition;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "clawTreatments")
public class ClawTreatment extends Base{

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateClawTreatment;
    private ClawPosition clawPosition;
}
