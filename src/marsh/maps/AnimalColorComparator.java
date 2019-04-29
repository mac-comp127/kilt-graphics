package marsh.maps;

import java.util.Comparator;

public class AnimalColorComparator implements Comparator<Animal> {
    @Override
    public int compare(Animal o1, Animal o2) {
        return o1.getColor().compareTo(o2.getColor());
    }
}
