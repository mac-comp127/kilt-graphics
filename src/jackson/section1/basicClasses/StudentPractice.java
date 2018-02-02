package jackson.section1.basicClasses;

public class StudentPractice {

    public static void main(String[] args){
        Student bret = new Student("Bret", 124, 1000);
        Student shilad = new Student("Shilad", 456, 1);

        System.out.print(bret.getName());
    }
}
