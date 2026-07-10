package nl.wiegersma.dairyfarm.dtos;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
public class DiseaseResponseDto extends BaseDto{
    private String name;
    private String description;
    private List<String> treatment;

}
