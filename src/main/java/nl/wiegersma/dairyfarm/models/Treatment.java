package nl.wiegersma.dairyfarm.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "treatments")
public class Treatment extends Base{
    private String dosage;
    private int duration;
    private LocalDate date;
    private String description;

    @ManyToOne
    @JoinColumn(name = "medication_Id")
    private Medication medication;

    @ManyToOne
    @JoinColumn(name = "medicationInventory_Id")
    private MedicationInventory medicationInventories;

    @ManyToOne
    @JoinColumn(name = "cow_Id")
    private Cow cow;

    @ManyToOne
    @JoinColumn(name = "clawTreatment_Id")
    private ClawTreatment clawTreatment;

}
