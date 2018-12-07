package cantrell.classExamples;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class SetExperiments {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("sally");
        set.add("fred");
        set.add("sally");
        System.out.println(set.contains("sally"));     // true
        System.out.println(set.contains("schwally"));  // false
        System.out.println(set.size());                // 2

        Scanner scanner = new Scanner(System.in);
        while(true) {
            set.add(scanner.nextLine());
            System.out.println(set.size() + " unique lines so far: " + set);
        }


    }
}
