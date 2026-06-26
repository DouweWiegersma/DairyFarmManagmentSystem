package nl.wiegersma.dairyfarm.dtos;
import lombok.Getter;
import lombok.Setter;
import java.util.HashMap;


@Setter
@Getter
public class ClawDiseaseResponseDto extends BaseDto{
    private String name;
    private String description;
    private HashMap<String, String> clawTreatment;

}
