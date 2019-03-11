package marsh.arrays;

public class Horse implements Animal, Comparable<Horse> {

    private String name;
    private boolean adult = false;

    public Horse(String name) {
        this.name = name;
    }

    public String vocalize() {
        return "Neigh!";
    }

    public String getName() {
        return name;
    }

    @Override
    public String[] dropOnKill() {
        String[] drops = new String[2];
        drops[0] = "leather";
        drops[1] = "hay";

        return drops;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void growUp() {
        adult = true;

    }

    public boolean isAdult() {
        return adult;
    }

    @Override
    public int compareTo(Horse o) {
        if(o != null) {
            return o.getName().compareToIgnoreCase(this.name);
        } else {
            throw new NullPointerException("Not a horse, null pointer!");
        }
    }
}
