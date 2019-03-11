package marsh.arraylists;

public interface Animal extends Comparable<Animal> {
    public String vocalize();
    public String getName();
    public String getSpecies();

}
