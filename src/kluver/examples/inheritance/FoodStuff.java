package kluver.examples.inheritance;

import java.time.Instant;
import java.util.Date;

public class FoodStuff extends Product {
    private Date expirationDate;

    public FoodStuff(String name, int amountInStock, double cost, Date expirationDate) {
        super(name, amountInStock, cost);
        this.expirationDate = expirationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public boolean checkIfSafe() {
        // checks if the current time is after the expiration date.
        return Instant.now().isAfter(expirationDate.toInstant());
    }

    public String toString() {
        return "foodStuff: name="+this.getName()+", expires: "+expirationDate;
    }
}
