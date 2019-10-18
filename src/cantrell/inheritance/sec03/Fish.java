package cantrell.inheritance.sec03;

public class Fish {
    public static void main(String[] args) {
        Fish a = new Fish();
        Fish b = new Apple();
        a.dance();
//        b.carp();
        b.dance();
    }

    public void dance() {
        System.out.println("Bustin’ a fish move");
    }
}

class Apple extends Fish {
    public void carp() {
        System.out.println("Well actually when are you goingna no that’s wrong blah blah");
    }

    public void dance() {
        System.out.println("Bustin’ an apple move");
    }
}
