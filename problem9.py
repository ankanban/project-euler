import math


'''
A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,

a2 + b2 = c2
For example, 32 + 42 = 9 + 16 = 25 = 52.

There exists exactly one Pythagorean triplet for which a + b + c = 1000.
Find the product abc.
'''


# a^2 + b^2 = c^2
# a + b + c = 1000
# a^2 + b^2 + c^2 + 2ab + 2ac + 2bc = 1000^2
# 2c^2 + 2ab + 2ac + 2bc = 1000^2
# c^2 + ab + ac + bc = 1000^2 / 2
# c  (a + b + c) + ab = 1000^2 / 2
# 1000 * c + ab = 1000^2 / 2
# c + ab / 1000 = 500
# c = 500 - (ab / 1000)
# c = (500000 - ab) / 1000   [c1]
# ab <= 500   [c2]

def is_square(apositiveint):
    x = apositiveint // 2
    seen = set([x])
    while x * x != apositiveint:
        x = (x + (apositiveint // x)) // 2
        if x in seen:
            return False
        seen.add(x)
    return True


def in_limit(a, b):
    if (a + b) > 1000:
        return False
    return True
    if (a * b) < 500000:
        return True
    return False


def get_abc():
    a = 1
    b = 1
    c = None
    seen = set([])
    for b in xrange(1, 1001):
        for a in xrange(b+1, 1001):
            if in_limit(a, b):
                c_sq = (a * a) + (b * b)
                if (is_square(c_sq)):
                    # c = (500000 - (a * b)) / 1000
                    c = int(math.sqrt(c_sq))
                    if c <= 1000 and c not in seen:
                        seen.add(c)
                        yield (a, b, c)


c_map = dict([(c, (a, b)) for (a, b, c) in get_abc()])

c_list = sorted(c_map.keys())

for c in c_list:
    (a, b) = c_map[c]
    print("---")
    print("%d + %d = %d" % (a*a, b*b, c * c))
    print("%d + %d + %d = %d" % (a, b, c, (a + b + c)))
    # print(a + b + c)
    # print("%d, %d" % (c, c_sq))
    print("---")

''''
    if (a + b + c) == 1000:
        return (a, b, c)
if c is not None:
    print("a = %d" % a)
    print("b = %d" % b)
    print("c = %d" % c)
    print("abc = %d" % (a * b * c))
else:
    print("Not found")
'''
