package nl.wiegersma.dairyfarm.models;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "treatments")
public class Treatment extends Base{
    private String dosage;
    private int duration;
    private LocalDate date;
    private String description;
}
