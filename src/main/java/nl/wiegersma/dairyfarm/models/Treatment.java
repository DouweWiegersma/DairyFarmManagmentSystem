package nl.wiegersma.dairyfarm.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


@Getter
@Setter
@Entity
@Table(name = "treatments")
public class Treatment extends Base{

    private int dosage;
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
    @JoinColumn(name = "disease_Id")
    private Disease disease;


}
