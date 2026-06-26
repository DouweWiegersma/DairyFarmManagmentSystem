package nl.wiegersma.dairyfarm.util;
import java.util.HashMap;
import java.util.List;

public final class ClawTreatmentHashMap {

    public ClawTreatmentHashMap() {
    }

    public static HashMap<String, String> addStep(List<String> addList) {
        HashMap<String, String> steps = new HashMap<>();

        if (addList == null || addList.isEmpty()) {
            return steps;
        }
        for (int i = 0; i < addList.size(); i++) {
            String stepKey = "Step" + (i + 1) + ": ";
            steps.put(stepKey, addList.get(i));
        }
        return steps;
    }}


