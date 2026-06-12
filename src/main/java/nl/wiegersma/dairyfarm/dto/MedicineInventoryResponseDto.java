package nl.wiegersma.dairyfarm.dto;

public record MedicineInventoryResponseDto(int batchNumber, int stockQuantity, BaseDto baseDto) {
}
