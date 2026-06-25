package nl.wiegersma.dairyfarm.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private List<String> clawTreatment;
}
