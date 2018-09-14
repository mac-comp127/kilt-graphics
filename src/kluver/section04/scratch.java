package kluver.section04;

public class scratch {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i+=1) {
            System.out.print("!");
            if (i %2 == 0) {
                continue;
            }
            System.out.print(i);
        }
    }
}
