package nl.wiegersma.dairyfarm.models;

import jakarta.persistence.*;

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

    @OneToOne(mappedBy = "cow")
    private CowPhoto cowPhoto;
}
