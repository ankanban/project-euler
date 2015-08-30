#!/usr/bin/env python
import sys
'''
10,001st prime
Problem 7
By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13,
we can see that the 6th prime is 13.

What is the 10,001st prime number?
'''

'''
Sieve of Sundaram:
For an interval for 1 to n:
1. eliminate all numbers such that:
i,j belongTo N, 1 <= i <= j
i + j + 2ij <= n
2. Then for remaining nos:
The remaining numbers are doubled and incremented by one, giving a list
of the odd prime numbers (i.e., all primes except 2) below 2n + 2.
'''


STATUS_SIGNS = 25


def sundaram_sieve_generator(n):
    modulo = n / STATUS_SIGNS
    slot = 0
    for j in xrange(1, n + 1):
        for i in xrange(1, j + 1):
            # print("(%d:%d)" % (i, j))
            s = i + j + (2 * i * j)
            # print(s)
            if s <= n:
                # print("%d:%d" % (s, n))
                yield s
        if j > (slot + modulo):
            slot += modulo
            sys.stdout.write('+')
            sys.stdout.flush()
    sys.stdout.write('\n')
    sys.stdout.flush()


def sundaram_sieve(n):
    num_set = [x for x in xrange(1, n + 1)]
    # print(num_set)
    sieve_set = [x for x in sundaram_sieve_generator(n)]
    filtered_set = set(num_set) - set(sieve_set)
    filtered_list = sorted(filtered_set)
    # print(sieve_set)
    return [2] + map(lambda x: (2 * x + 1), filtered_list)

if __name__ == "__main__":
    primes = sundaram_sieve(60000)
    print(primes)
    l = len(primes)
    print("No. of primes: %d" % l)
    if l > 10000:
        print("10001st Prime: %d" % primes[10000])
