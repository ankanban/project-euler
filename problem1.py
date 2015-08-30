# Multiples of 3 and 5
# Problem 1
#
# If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
#
# Find the sum of all the multiples of 3 or 5 below 1000.
#
# Solution:
# sum(multiples of 3 or 5) = sum(multiples of 3) + sum(multiples of 5) - sum(multiples of 15)


def sum_multiples_of(factor, less_than):
	n = (less_than - 1) // factor
	return factor * ((n * (n + 1)) // 2)

if __name__=="__main__":
	num1 = 3
	num2 = 5
	less_than = 1000
	print(sum_multiples_of(num1, less_than) + sum_multiples_of(num2, less_than) - sum_multiples_of(num1 * num2, less_than))