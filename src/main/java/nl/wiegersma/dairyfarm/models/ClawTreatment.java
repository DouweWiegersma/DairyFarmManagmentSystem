package nl.wiegersma.dairyfarm.models;


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
    private LocalDate date;
    private ClawPosition clawPosition;
}
