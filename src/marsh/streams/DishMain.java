package marsh.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class DishMain {

    public static void main(String[] args) {

        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH) );


        List<String> dishList =
                menu.stream()
                        .filter(d -> d.getCalories() > 300)
                        .sorted(comparing(Dish::getName))
                        .map(Dish::getName)
                        .limit(4)
                        .collect(toList());
        System.out.println(dishList);

//        Map<Dish.Type, List<Dish>> dishMap =
//                menu.stream()
//                        .collect(groupingBy(Dish::getType));
//        System.out.println(dishMap);
    }
}
