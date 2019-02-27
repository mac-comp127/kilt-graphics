package sen.animals2;

public class AnimalMain {
    public static void main(String [] args) {
        Animal [] pets = new Animal[2];
        pets[0] = new Dog("fido", "brown");
        pets[1] = new Cat("tom", "gray");
        for (Animal p : pets) {
            System.out.println("pet  " + p.getName() + " says " + p.vocalize());
        }
    }
}
