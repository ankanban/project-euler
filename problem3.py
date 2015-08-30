# Largest prime factor
# Problem 3
#
# The prime factors of 13195 are 5, 7, 13 and 29.
#
# What is the largest prime factor of the number 600851475143 ?
#
# http://en.wikipedia.org/wiki/Quadratic_sieve
#
def is_prime(n):
	if n % 2 == 0:
		return False
	div = 3
	while True:
		if (n % div == 0):
			#print("divisible by " + str(div))
			return False
		div += 2
		if div > (n // 2):
			#print(str(div) + " > " + str(n // 2))
			break
	return True

def largest_prime_factor_of(n):
	largest_prime_factor = 1
	if n % 2 == 0:
		largest_prime_factor = 2
	divisor = 3
	while True:
		quotient = n // divisor
		remainder = n % divisor
		if remainder == 0 and is_prime(divisor):
			largest_prime_factor = divisor
			print(str(largest_prime_factor) + ", with quotient = " + str(quotient))
		if remainder == 0 and (quotient < divisor) and is_prime(quotient):
			print(str(divisor) + ", with quotient = " + str(quotient) + ", but " + str(quotient) + " < " + str(divisor))
			break
		divisor += 2
	return largest_prime_factor

if __name__=="__main__":
	ans = largest_prime_factor_of(600851475143)
	isPrime = is_prime(ans)
	print("Ans: " + str(ans) + (" and it is a prime" if isPrime else "but it is not a prime :("))