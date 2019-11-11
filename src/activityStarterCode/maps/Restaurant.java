package activityStarterCode.maps;

/**
 * Information about a restaurant.
 */
class Restaurant {
    private String name;
    private String cuisine;
    private int rating;

    /**
     * Creates a new restaurant
     * @param name      The name of the restaurant (e.g. Shish)
     * @param cuisine   The type of food (e.g. Mediterranean)
     * @param rating    The quality rating between 1 and 5 (e.g. 4)
     */
    public Restaurant(String name, String cuisine, int rating) {
        this.name = name;
        this.cuisine = cuisine;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public String getCuisine() {
        return cuisine;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", cuisine='" + cuisine + '\'' +
                ", rating=" + rating +
                '}';
    }
}
