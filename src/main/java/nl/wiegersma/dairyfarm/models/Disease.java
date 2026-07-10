package nl.wiegersma.dairyfarm.models;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Diseases")
public class Disease extends Base{
    private String name;
    private String description;
    private List<String> treatment;

    @OneToMany(mappedBy = "disease")
    private List<ClawTreatment> clawTreatments;

    @OneToMany(mappedBy = "disease")
    private List<Treatment> treatments;

}
