import random
import time


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
