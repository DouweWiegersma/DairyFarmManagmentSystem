package nl.wiegersma.dairyfarm.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CowResponseDto extends BaseDto{
    private int earTag;
    private boolean aLife;

}
