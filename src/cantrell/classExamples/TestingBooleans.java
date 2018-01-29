package cantrell.classExamples;

public class TestingBooleans {
    public static void main(String[] args) {
        booleanFunFunFun(true);
        booleanFunFunFun(false);
    }

    private static void booleanFunFunFun(boolean isAlive) {

        // instead of: if(isAlive == true)
        //         do: if(isAlive)

        // instead of: if(isAlive == false)
        //         do: if(!isAlive)

        if(!isAlive) {
            System.out.println("Awwwww. :(");
        } else {
            System.out.println("IT'S ALIIIIIIIVE!!!!!!!!!!!!");
        }
    }
}






















