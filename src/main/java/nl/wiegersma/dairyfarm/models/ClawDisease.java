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
@Table(name = "clawDiseases")
public class ClawDisease extends Base{
    private String name;
    private String description;
    private List<String> treatment;

    @OneToMany(mappedBy = "clawDisease")
    private List<ClawTreatment> clawTreatments;

}
