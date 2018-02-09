package jackson.section2.inheritanceExample;

/**
 * Created by bjackson on 9/21/2016.
 */
public class PointProgram {

    public static void main(String[] args){
        Point p1 = new Point(10, 10);
        System.out.println(p1);

        Point3D p2 = new Point3D(0, 0, 50);
        System.out.println(p2);


    }
}
