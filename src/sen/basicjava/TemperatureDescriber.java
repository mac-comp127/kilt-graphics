package sen.basicjava;

import java.util.Scanner;

public class TemperatureDescriber {
    public static void main(String []args) {
        System.out.print("Please enter a number in degrees Celsius: ");

        Scanner scan = new Scanner(System.in);
        int degreesCelcius = scan.nextInt();

        String description;
        if (degreesCelcius > 27) {
            description = "hot";
        } else if (degreesCelcius > 15) {
            description = "perfect";
        } else if (degreesCelcius > 0) {
            description = "chilly";
        } else if (degreesCelcius > -20) {
            description = "cold";
        } else if (degreesCelcius > -40) {
            description = "frigid";
        } else {
            description = "brutal";
        }

        System.out.println(
                degreesCelcius +
                        " degrees celsius feels " +
                        description);
    }
}
