package marsh.arraylists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListMain {

    public static void main(String[] args) {
        //Create a list of animals
        List<Animal> animals = new ArrayList<Animal>();


        //Add animals to list

        animals.add(new Horse("Harry"));
        animals.add(new Cow("Daisy"));
        animals.add(new Horse("Harriet"));
        animals.add(new Cow("Harry"));

        Horse harold = new Horse("Harold");

        animals.add(harold);



        //sort the list
        Collections.sort(animals);

        //Print the list, iterate through the list
        System.out.println(animals.toString());


        //Remove items by index, object
//        animals.remove(1);
//        System.out.println(animals.toString());

        animals.remove(harold);
        System.out.println(animals.toString());


        for(Animal a: animals) {
            System.out.println(a.getName() + animals.indexOf(a));
        }

        for(int i = 0; i < animals.size(); i++) {
            System.out.print(animals.get(i).vocalize());
        }




    }
}
