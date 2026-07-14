package nl.wiegersma.dairyfarm.dtos;
import lombok.Getter;
import lombok.Setter;
import nl.wiegersma.dairyfarm.enums.Time;

@Getter
@Setter
public class MedicationResponseDto extends BaseDto {
    private String name;
    private String description;
    private Long milkWithholdingPeriod;
    private Long meatWithdrawalPeriod;
    private Time time;

    public String getWaitTimeMeat() {
        return meatWithdrawalPeriod + " " + time.toString().toLowerCase();
    }

    public String getWaitTimeMilk() {
        return milkWithholdingPeriod + " " + time.toString().toLowerCase();
    }

}
