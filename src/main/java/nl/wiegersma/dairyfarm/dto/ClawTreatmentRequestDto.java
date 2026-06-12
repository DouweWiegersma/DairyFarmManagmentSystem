package nl.wiegersma.dairyfarm.dto;

import nl.wiegersma.dairyfarm.enums.ClawPosition;

import java.time.LocalDate;

public record ClawTreatmentRequestDto(LocalDate dateHoofTreatment, ClawPosition clawPosition) {
}
