package sen.animals3;

public interface Animal extends Comparable<Animal> {
    public String vocalize();
    public String getName();
    public String getSpecies();
}
