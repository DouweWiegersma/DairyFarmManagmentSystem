package nl.wiegersma.dairyfarm.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import nl.wiegersma.dairyfarm.enums.Time;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "medicines")
public class Medication extends Base {
    private String name;
    private String description;
    private Long milkWithholdingPeriod;
    private Long meatWithdrawalPeriod;
    @Enumerated(EnumType.STRING)
    private Time time;

    @OneToMany(mappedBy = "medication")
    private List<MedicationInventory> medicationInventories;

    @OneToMany(mappedBy = "medication")
    private List<Treatment> treatment;




}
