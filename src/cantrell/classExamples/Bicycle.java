package cantrell.classExamples;

public class Bicycle {
    private Wheel frontWheel, rearWheel;

    public Bicycle(float frontMaxPSI, float rearMaxPSI) {
        frontWheel = new Wheel(frontMaxPSI);
        rearWheel = new Wheel(rearMaxPSI);
    }

    public Wheel getFrontWheel() {
        return frontWheel;
    }

    public Wheel getRearWheel() {
        return rearWheel;
    }

    private boolean isReadyToRide() {
        return frontWheel.isReadyToRide()
            && rearWheel.isReadyToRide();
    }

    @Override
    public String toString() {
        return "Bicycle("
            + "front=" + getFrontWheel()
            + " rear=" + getRearWheel()
            + ")";
    }

    public static void main(String[] args) {
        Bicycle bike = new Bicycle(30, 70);
        System.out.println("Bike ready to ride? " + bike.isReadyToRide() + "   " + bike);
        bike.getFrontWheel().inflate();
        System.out.println("Bike ready to ride now? " + bike.isReadyToRide() + "   " + bike);
        bike.getRearWheel().inflate();
        System.out.println("Bike ready to ride NOOOOW? " + bike.isReadyToRide() + "   " + bike);
    }
}

class Wheel {
    private float maxPSI, currentPSI;

    public Wheel(float maxPSI) {
        this.maxPSI = maxPSI;
    }

    public float getMaxPSI() {
        return maxPSI;
    }

    public float getCurrentPSI() {
        return currentPSI;
    }

    public void inflate() {
        currentPSI = maxPSI;
    }

    public boolean isReadyToRide() {
        return currentPSI >= maxPSI * 0.6;
    }

    @Override
    public String toString() {
        return "Wheel(" + getCurrentPSI() + "/" + getMaxPSI() + ")";
    }
}



/* •••••••• BAD VERSION ••••••••

public class Bicycle {
    private float frontMaxPSI, frontCurrentPSI, rearMaxPSI, rearCurrentPSI;
    public Bicycle(float frontMaxPSI, float rearMaxPSI) {
        this.frontMaxPSI = frontMaxPSI;
        this.rearMaxPSI = rearMaxPSI;
    }

    public float getFrontMaxPSI() {
        return frontMaxPSI;
    }

    public float getFrontCurrentPSI() {
        return frontCurrentPSI;
    }

    public float getRearMaxPSI() {
        return rearMaxPSI;
    }

    public float getRearCurrentPSI() {
        return rearCurrentPSI;
    }

    public void inflateFront() {
        frontCurrentPSI = frontMaxPSI;
    }

    public void inflateRear() {
        rearCurrentPSI = rearMaxPSI;
    }

    private boolean isReadyToRide() {
        return frontCurrentPSI >= frontMaxPSI * 0.6
            &&  rearCurrentPSI >=  rearMaxPSI * 0.6;
    }

    @Override
    public String toString() {
        return "Bicycle("
            + "front=" + getFrontCurrentPSI() + "/" + getFrontMaxPSI()
            + " rear=" + getRearCurrentPSI() + "/" + getRearMaxPSI()
            + ")";
    }

    public static void main(String[] args) {
        Bicycle bike = new Bicycle(30, 70);
        System.out.println("Bike ready to ride? " + bike.isReadyToRide() + "   " + bike);
        bike.inflateFront();
        System.out.println("Bike ready to ride now? " + bike.isReadyToRide() + "   " + bike);
        bike.inflateRear();
        System.out.println("Bike ready to ride NOOOOW? " + bike.isReadyToRide() + "   " + bike);
    }
}

*/