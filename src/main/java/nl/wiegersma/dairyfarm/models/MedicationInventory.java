package nl.wiegersma.dairyfarm.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "medicationInventory")
public class MedicationInventory extends Base{

    private int batchNumber;
    private int stockQuantity;

    @ManyToOne
    @JoinColumn(name = "medication_Id")
    private Medication medication;

    @OneToMany(mappedBy = "medicationInventories")
    private List<Treatment> treatment;



}
