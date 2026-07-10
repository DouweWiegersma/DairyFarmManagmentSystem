package nl.wiegersma.dairyfarm.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class CowRequestDto{

    @NotNull(message = "cow number is required")
    @Size(max = 4, min = 4, message = "cow number needs 4 characters")
    private Long cowNumber;

    private boolean aLife;

}
