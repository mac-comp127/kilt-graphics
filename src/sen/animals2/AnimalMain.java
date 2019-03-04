package sen.animals2;

public class AnimalMain {
    public static void main(String [] args) {
        Animal [] pets = new Animal[2];
        Animal fido = new Dog("fido", "brown");
        pets[0] = fido;
        pets[1] = new Cat("tom", "gray");
        for (Animal p : pets) {
            System.out.println("pet  " + p.getName() + " says " + p.vocalize());
        }
    }
}
