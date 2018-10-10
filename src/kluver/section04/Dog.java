package kluver.section04;

public class Dog {
    private String name;
    private int age;

    private static int numberOfDogs = 0;

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;

        numberOfDogs++;
        int dogNumber = 2*numberOfDogs;
    }

    public Dog(String name) {
        this(name, 1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public void feed() {
        System.out.println("You feed "+name+" they LOVE it");
    }

    public static void main(String[] args) {
        Dog dougie = new Dog("Dougie", 12);
    }
}
