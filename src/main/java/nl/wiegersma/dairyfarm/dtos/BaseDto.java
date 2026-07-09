package nl.wiegersma.dairyfarm.dtos;

import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@Setter
public abstract class BaseDto {
    private Long id;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
