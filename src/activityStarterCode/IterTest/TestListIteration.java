package activityStarterCode.IterTest;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class TestListIteration {

    @Test
    public void testDoNTimesCopy() {
        // Create a "source" array with some names
        List<String> minnesotaReps = new ArrayList<>(Arrays.asList(
                "Hagedorn", "Craig", "Phillips", "McCollum",
                "Omar", "Emmer", "Peterson", "Stauber"));

        // Create a copy of the array using a do-n-times loop
        List<String> result = new ArrayList<>();

        for (int i = 0; i < minnesotaReps.size(); i++) {
            String rep = minnesotaReps.get(i);
            result.add(rep);
        }

        // Make sure the copy is correct
        assertEquals(
                result,
                Arrays.asList(
                        "Hagedorn", "Craig", "Phillips", "McCollum",
                        "Omar", "Emmer", "Peterson", "Stauber")
        );
    }

}
