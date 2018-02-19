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

    @Override
    public String toString() {
        return "Product "+name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Product)) {
            return false;
        } else {
            Product other = (Product) obj;
            //this point
            if (!this.name.equals(other.name)) {
                return false;
            } else if(this.cost != other.cost) {
                return false;
            } else {
                return true;
            }
        }

    }

    public static void main(String[] args) {
        Product prod = new Product("Doug", 1, 1.25);
        Product prod2 = new Product("Doug", 10, 1+0.25);
        System.out.println(prod.equals(prod2));
    }
}




















