package nl.wiegersma.dairyfarm.dtos;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ClawDiseaseRequestDto {
    private String name;
    private String description;
    private List<String> clawTreatment;
}
