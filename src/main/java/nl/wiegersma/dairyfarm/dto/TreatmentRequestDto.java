package nl.wiegersma.dairyfarm.dto;

import java.time.LocalDate;

public record TreatmentRequestDto(String dosage, int duration, LocalDate date, String description) {
}
