package jackson.section2.inheritanceExample;

/**
 * Created by bjackson on 9/21/2016.
 */
public class Point3D extends Point {
    private double z;


    public Point3D(double x, double y, double z){
        super(x, y);
        this.z = z;
    }

    @Override
    public String toString() {
        //return "This is a 3d point at ("+getX()+", "+y+ ", "+z+")";
        return "This is a 3d point at "+super.toString()+ "with z at "+z;

    }
}
