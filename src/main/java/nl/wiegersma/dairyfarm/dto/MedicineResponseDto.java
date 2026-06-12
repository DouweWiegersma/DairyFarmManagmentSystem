package nl.wiegersma.dairyfarm.dto;

public record MedicineResponseDto(String name, String description, int milkWithholdingPeriod, int meatWithdrawalPeriod, BaseDto baseDto) {
}
