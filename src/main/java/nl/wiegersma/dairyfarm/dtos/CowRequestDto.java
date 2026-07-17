package nl.wiegersma.dairyfarm.dtos;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class CowRequestDto{

    @NotNull(message = "cow number is required")
    @Digits(integer = 4, message = "cow number needs 4 characters", fraction = 0)
    private Long cowNumber;

    private boolean aLife;

}
