package nl.wiegersma.dairyfarm.dtos;

import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@Setter
public class BaseDto {
    private long id;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
