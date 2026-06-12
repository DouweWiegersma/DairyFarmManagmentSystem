package nl.wiegersma.dairyfarm.dto;

import java.time.LocalDate;

public record BaseDto(long id, LocalDate createdAt, LocalDate updatedAt) {
}
