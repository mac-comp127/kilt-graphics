package cantrell.classExamples;

public class CupOfTea {

    public static final float MAXIMUM_REASONABLE_CUP_SIZE = 3000;

    private float teaQuantity;   // mL
    private float milkQuantity;  // mL
    private float sugarQuantity; // g

    public CupOfTea() {
        teaQuantity = 0;
        milkQuantity = 0;
        sugarQuantity = 0;

        System.out.println(Math.PI * Math.E);
    }

    public void everlastingFill() {
        teaQuantity = Float.POSITIVE_INFINITY;
        milkQuantity = Float.POSITIVE_INFINITY;
        sugarQuantity = Float.POSITIVE_INFINITY;
    }


    public static void main(String[] args) {
        CupOfTea
            sallyCup = new CupOfTea(),
            fredCup = new CupOfTea();
        sallyCup.everlastingFill();
    }


}









