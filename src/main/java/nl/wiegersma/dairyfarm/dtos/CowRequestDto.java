package nl.wiegersma.dairyfarm.dtos;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class CowRequestDto{
    private Long cowNumber;
    private boolean aLife;

}
