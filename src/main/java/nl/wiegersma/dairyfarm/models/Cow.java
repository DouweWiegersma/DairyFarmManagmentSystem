package nl.wiegersma.dairyfarm.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cows")
public class Cow extends Base{

    private Long cowNumber;
    private boolean aLife;

    @OneToMany(mappedBy = "cow", cascade = CascadeType.ALL)
    private List<ClawTreatment> clawTreatments;

    @OneToMany(mappedBy = "cow", cascade = CascadeType.ALL)
    private List<Treatment> treatment;
}
