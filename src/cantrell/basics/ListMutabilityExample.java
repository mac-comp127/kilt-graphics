package cantrell.basics;

import java.util.ArrayList;
import java.util.List;

public class ListMutabilityExample {
    public static void printBulletList(List<String> items) {
        for(String item : items) {
            System.out.println("𒁊 " + item);
        }
        items.add("kelp");   // unexpected modification
        items.add("nails");
    }

    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();
        fruits.add("dragonfruit");
        printBulletList(fruits);

        System.out.println("––––––––––––––––––––––––––");
        fruits.add("rambutan");
        printBulletList(fruits);

        System.out.println("––––––––––––––––––––––––––");
        fruits.add("starfruit");
        printBulletList(fruits);

        System.out.println("––––––––––––––––––––––––––");
        fruits.add("rhubarb");
        printBulletList(fruits);

        System.out.println("––––––––––––––––––––––––––");
        fruits.add("cucumbers");
        printBulletList(fruits);
    }
}
