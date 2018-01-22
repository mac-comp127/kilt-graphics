package jackson.section1.basicJava;

public class VariableScope {

    //Member vars are declared outside of a method but inside of a class
    private int memberVar;

    public static void main(String[] args){
        int localVar = 10;


        if(localVar == 10){
            localVar++;
            int localVar2 = 5;
        }
        //The following line is not allowed because it is outside
        // of the block where localVar2 was declared.
        //localVar2++;

    }

    public void method2(){
        //localVar++;
        memberVar = 10;
    }

    public void method3(){
        memberVar++;
    }
}
