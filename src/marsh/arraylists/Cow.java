package marsh.arraylists;

public class Cow implements Animal, Comparable<Animal> {

    private String name;

    public Cow(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String vocalize() {
        return "Moo!!";
    }

    public String getSpecies() {
        return "Cow";
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
            throw new NullPointerException("Not a cow, null pointer!");
        }
    }
}
