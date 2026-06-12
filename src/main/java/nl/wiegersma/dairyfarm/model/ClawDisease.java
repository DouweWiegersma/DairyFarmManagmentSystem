package nl.wiegersma.dairyfarm.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "clawDiseases")
public class ClawDisease extends Base{
    private String name;
    private String description;
    private String clawTreatment;
}
