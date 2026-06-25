package nl.wiegersma.dairyfarm.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "medicines")
public class Medicine extends Base {
    private String name;
    private String description;
    private int milkWithholdingPeriod;
    private int meatWithdrawalPeriod;
}
