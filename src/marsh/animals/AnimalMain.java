package marsh.animals;

public class AnimalMain {
    public static void main(String [] args) {
        Dog fido = new Dog("fido", "brown");
        System.out.println(fido);
        System.out.println("fido says: " + fido.vocalize());
        Cat tom = new Cat("tom", "gray");
        System.out.println(tom);
        System.out.println("tom says: " + tom.vocalize());
    }
}
