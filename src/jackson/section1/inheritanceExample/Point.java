package jackson.section1.inheritanceExample;

/**
 * Represents a 2D point
 * Created by bjackson on 9/21/2016.
 */
public class Point {
    protected double x;
    protected double y;


    public Point(){
        this(5,0);
    }


    public Point(String name){
        this(0,5);
    }

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return "("+x+", "+y+")";
    }

    public double getX(){
        return x;
    }
}
