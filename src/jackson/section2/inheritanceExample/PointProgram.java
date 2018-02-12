package jackson.section2.inheritanceExample;

/**
 * Created by bjackson on 9/21/2016.
 */
public class PointProgram {

    public static void main(String[] args){
        Point p1 = new Point(10, 10);
        Point p3 = new Point(10, 10);
        p3 = p1;
        System.out.println(p1);

        Point3D p2 = new Point3D(0, 0, 50);
        System.out.println(p2);

        if (p1 == p3){
            System.out.println("Equal");
        }
        else {
            System.out.println("Not equal");
        }


    }
}
