package nl.wiegersma.dairyfarm.dto;

import nl.wiegersma.dairyfarm.enums.ClawPosition;

import java.time.LocalDate;

public record ClawTreatmentResponseDto(LocalDate dateHoofTreatment, ClawPosition clawPosition, BaseDto baseDto) {
}
