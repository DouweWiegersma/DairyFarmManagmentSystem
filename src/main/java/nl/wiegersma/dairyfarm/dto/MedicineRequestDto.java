package nl.wiegersma.dairyfarm.dto;

public record MedicineRequestDto(String name, String description, int milkWithholdingPeriod, int meatWithdrawalPeriod) {
}
