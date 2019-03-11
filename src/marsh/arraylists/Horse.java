package marsh.arraylists;

public class Horse implements Animal, Comparable<Animal> {

    private String name;

    public Horse(String name) {
        this.name = name;
    }

    public String vocalize() {
        return "Neigh!";
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return "Horse";
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Animal o) {
        if(o != null) {
            int result = this.getName().compareTo(o.getName());
            if (result == 0) {
                result = this.getSpecies().compareTo(o.getSpecies());
            }
            return result;
        } else {
            throw new NullPointerException("Not a horse, null pointer!");
        }
    }
}
