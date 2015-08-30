import sys
from blist import sortedset

'''
The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.

Find the sum of all the primes below two million.
'''


STATUS_SIGNS = 80


def eratosthenes_sieve(n):
    modulo = n / STATUS_SIGNS
    print(modulo)
    iterno = 0

    # primes = set([])
    composites = [False for x in xrange(2, n + 1)]

    def is_composite(num):
        return composites[num - 2]

    # Initial condition
    smp = 2
    composites[smp - 2] = False

    while smp <= n:  # Invariant
        # Find the next prime
        while smp <= n and is_composite(smp):
            smp += 1

        # Check bounds
        if smp > n:
            # All numbers exhausted
            break

        # Add this prime
        yield smp

        # Now add this prime's multiples to the set of composites.
        for x in xrange(smp * smp, n + 1, smp):
            composites[x - 2] = True

        # move to the next number
        smp += 1
        # Print stats
        if iterno > modulo and iterno % modulo == 0:
            # print(iterno)
            sys.stdout.write('+')
            sys.stdout.flush()
    sys.stdout.write('\n')
    sys.stdout.flush()


def sundaram_sieve_generator(n):
    modulo = (n * (n + 1) / 2) / STATUS_SIGNS
    print(modulo)
    slot = 0
    iterno = 0
    for j in xrange(1, n + 1):
        for i in xrange(1, j + 1):
            # print("(%d:%d)" % (i, j))
            s = i + j + (2 * i * j)
            # print(s)
            if s <= n:
                # print("%d:%d" % (s, n))
                yield s
            iterno += 1
            if iterno % modulo == 0:
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
    # import json
    # with open("all_primes.json", "w") as pf:
    #    json.dump([p for p in eratosthenes_sieve(2000000)], pf)
    n = 0
    sum_p = 0
    for p in eratosthenes_sieve(2000001):
        sum_p += p
        n += 1

    print("Num primes: %d" % n)
    print("Sum: %d" % sum_p)
