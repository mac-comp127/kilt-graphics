package sen.arrays;

public class ArrayExamples {
    public static void main(String args[]) {
        int [] nums = new int[10];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = i;
        }

        int x = 20;
        for (int i = nums.length - 1; i > 0; i--) {
            nums[i] = nums[i - 1];
        }
        nums[0] = x;

        System.out.print("[");
        for (int i = 0; i < nums.length; i++) {
           System.out.print(nums[i] + ", ");
        }
        System.out.println("]");
    }
}
