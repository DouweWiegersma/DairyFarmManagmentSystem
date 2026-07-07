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
@Table(name = "medicines")
public class Medication extends Base {
    private String name;
    private String description;
    private Long milkWithholdingPeriod;
    private Long meatWithdrawalPeriod;

    @OneToMany(mappedBy = "medication")
    private List<MedicationInventory> medicationInventories;

    @OneToMany(mappedBy = "medication")
    private List<Treatment> treatment;




}
