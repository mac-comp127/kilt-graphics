package kluver.examples.inheritance;

public class Product {
    private String name;
    private int amountInStock;
    private double cost;

    public Product(String name, int amountInStock, double cost) {
        this.name = name;
        this.amountInStock = amountInStock;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getAmountInStock() {
        return amountInStock;
    }

    public double getCost() {
        return cost;
    }

    public void sell() {
        amountInStock --;
    }
}
