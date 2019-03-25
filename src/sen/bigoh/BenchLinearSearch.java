package sen.bigoh;

import java.util.Random;

/* Reproduce the following Python code:

def linear_search(A, target):
    for (i, e) in enumerate(A):
    	if e == target:
    		return i
    return -1


if __name__ == '__main__':
	N = 10000000
	num_trials = 10
	max_num = N * 10
	A = [random.randint(0, max_num) for i in range(N)]

	t0 = time.time()
	for i in range(num_trials):
		linear_search(A, random.randint(0, max_num))
	t1 = time.time()
	print((t1 - t0) / num_trials)

 *
 */
public class BenchLinearSearch {
    public static int linearSearch(int [] A, int target) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void runExperiment(int n) {
        int numTrials = 1000;
        int maxNum = n * 10;
        int A [] = new int[n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            A[i] = random.nextInt(maxNum);
        }

        long t0 = System.currentTimeMillis();
        for (int i = 0; i < numTrials; i++) {
            linearSearch(A, random.nextInt(maxNum));
        }
        long t1 = System.currentTimeMillis();
        double elapsed = (t1 - t0) / 1000.0 / numTrials;

        System.out.println(
                "Average linear search in " +
                n + " elements took " + elapsed);

    }

    public static void main(String args[]) {
        for (int n = 1000;; n *= 10) {
            runExperiment(n);
        }
    }
}
