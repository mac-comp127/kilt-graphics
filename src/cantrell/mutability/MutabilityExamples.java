package cantrell.mutability;

import java.util.ArrayList;
import java.util.List;

public class MutabilityExamples {
    public static void main(String[] args) {
        String originalName = "Sally";
        String secondName = originalName;
        secondName = secondName.replace("S", "Zzz");

        System.out.println(originalName);
        System.out.println(secondName);

        // --- versus ----

        List<String> originalList = new ArrayList<>();
        originalList.add("hi");
        List<String> secondList = originalList;
        secondList.add("Have a great Fall Break!!");
    }
}
