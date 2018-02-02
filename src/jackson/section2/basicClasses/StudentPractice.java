package jackson.section2.basicClasses;

public class StudentPractice {
    public static void main(String[] args){
        Student bret = new Student("Bret", 123, 10000);
        Student libby = new Student ("Libby", 456, 100000);

        if (bret.isGraduated()) {
            System.out.print(bret.getName());
        }
    }
}
