package nl.wiegersma.dairyfarm.dto;

import java.time.LocalDate;

public record TreatmentResponseDto(String dosage, int duration, LocalDate date, String description, BaseDto baseDto) {
}
