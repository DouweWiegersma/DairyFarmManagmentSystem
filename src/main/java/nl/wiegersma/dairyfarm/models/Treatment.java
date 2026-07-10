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

    @Min(1)
    @Max(90)
    private int dosage;

    @Min(1)
    @Max(3)
    private int duration;
    @NotNull(message = "Datum moet worden ingevuld")
    private LocalDate date;

    private String description;

    @ManyToOne
    @NotNull(message = "medicatie is niet ingevuld")
    @JoinColumn(name = "medication_Id")
    private Medication medication;

    @ManyToOne
    @NotNull(message = "kies uit de inventory met welk flesje/injector of dergelijke je deze koe wilt behandelen")
    @JoinColumn(name = "medicationInventory_Id")
    private MedicationInventory medicationInventories;

    @ManyToOne
    @NotNull(message = "selecteer een koe")
    @JoinColumn(name = "cow_Id")
    private Cow cow;

//    @ManyToOne
//    @JoinColumn(name = "clawTreatment_Id")
//    private ClawTreatment clawTreatment;

    @ManyToOne
    @JoinColumn(name = "disease_Id")
    private Disease disease;


}
