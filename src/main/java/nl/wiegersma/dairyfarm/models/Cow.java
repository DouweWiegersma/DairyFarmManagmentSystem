package nl.wiegersma.dairyfarm.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cows")
public class Cow extends Base{

    private int cowNumber;
    private boolean aLife;


}
