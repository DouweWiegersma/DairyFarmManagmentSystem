package nl.wiegersma.dairyfarm.models;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import nl.wiegersma.dairyfarm.enums.ClawPosition;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "clawTreatments")
public class ClawTreatment extends Base{

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateClawTreatment;
    private ClawPosition clawPosition;

    @ManyToOne
    @JoinColumn(name = "disease_id")
    private ClawDisease clawDisease;


    @OneToMany(mappedBy = "clawTreatment")
    private List<Treatment> treatment;


    @ManyToOne
    @JoinColumn(name = "cowId")
    private Cow cow;

}
