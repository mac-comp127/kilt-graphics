package cantrell.classExamples;

/**
 * Created by paul on 2018/1/24.
 */
public class Loops {
    public static void main(String[] args) {
        cheerleadEasy("Macalester");
        cheerleadEasy("snow");
    }

    public static void cheerleadHard(String cheer) {
        for(int index = 0; index < cheer.length(); index++) {
            char letter = cheer.charAt(index);
            System.out.println("GIMME A(N) [[ " + letter + " ]]!");
        }
    }

    public static void cheerleadEasy(String cheer) {
        for(char letter : cheer.toCharArray()) {
            System.out.println("GIMME A(N) [[ " + letter + " ]]!");
        }
    }
}

