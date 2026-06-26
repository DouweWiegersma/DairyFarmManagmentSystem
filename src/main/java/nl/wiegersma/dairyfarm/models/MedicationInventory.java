package nl.wiegersma.dairyfarm.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "medicationInventory")
public class MedicationInventory extends Base{

    private int batchNumber;
    private int stockQuantity;

}
