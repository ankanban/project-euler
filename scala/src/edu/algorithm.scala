package edu
import scala.annotation.tailrec

object algorithm {
  
  
  def product(f: Int => Int)(a: Int, b : Int) : Int = {
    @tailrec
    def loop(b: Int, acc: Int): Int = {
      if (b < a) acc
      else loop(b-1, f(b)* acc)
    }
    loop(b, 1)
  }
  
  def factorial_curried(n: Int): Int = product(x => x)(1, n)
  
  
  def factorial(n : Int) : Int = {
    @tailrec
  	def multiply_tail(p : Int, n : Int) : Int =
  		if (n == 1) p else multiply_tail(p * n, n - 1)
  		
  	if (n < 0)
  		throw new IllegalArgumentException
  	else
  		if (n == 1 || n == 0) 1 else multiply_tail(n, n-1)
  }

  // Type of sum: (Int => Int) => Int, Int => Int
  def sum(f: Int => Int)(a: Int, b: Int): Int = {
    // Type: (Int, Int) => Int
    @tailrec
    def loop(a: Int, acc: Int): Int = {
      if (a > b) acc
      else loop(a+1, acc + f(a))
    }
    loop(a, 0)
  }

  def sum_of_fact(n : Int) = sum(factorial_curried)(1, n)
  
  
  def mapReduce(f: Int => Int, combine: (Int, Int) => Int, zero: Int)(a:Int, b: Int): Int = {
    @tailrec
    def combine_loop(a: Int, acc: Int): Int = 
      if (a > b) acc
      else combine_loop(a+1, combine(acc, f(a)))
    combine_loop(a, zero)
  }
  
  def sum_of_fact_mr(n: Int) = mapReduce(factorial_curried, (a: Int, b:Int) => a + b, 0)(1, n)
  
  /**
   * Project Euler 4
   * A palindromic number reads the same both ways.
   * The largest palindrome made from the product of two 2-digit
   * numbers is 9009 = 91 × 99.
   * Find the largest palindrome made from the product of two
   * 3-digit numbers.
   */
	def largest_palindrome_product(low : Int, high : Int) : Int = {
	
 	 def is_palindrome(n : Int) : Boolean = {
 	 
		def reverse(n : Int) : Int = {
		 	@tailrec
		 	def reverse_it(n : Int, nr : Int) : Int = if (n == 0) nr else reverse_it( n / 10, (nr * 10) + (n % 10))
		 	reverse_it(n, 0)
		 }
		 
		 if (n < 0)
		 	throw new IllegalArgumentException("Number is not a positive Integer")
		 else
			n == reverse(n)
	  }
	    
 	 @tailrec
 	 def visit_number_pair(m : Int, n : Int, m_into_n : Int, end : Int, largest_pali : Int) : Int =
	 	  	if (n > end)
	 	  		largest_pali
	 	  	else
	 	  		if (is_palindrome(m_into_n) && (m_into_n) > largest_pali)
	 	  			visit_number_pair(m, n+1, m * (n+1), end, m * n)
	 	  		else
	 	  			visit_number_pair(m, n+1, m * (n + 1), end, largest_pali)
	 	 
 	 	def visit_number(low: Int, high : Int, largest_pali : Int) : Int =
 	 		if (low > high)
 	 			largest_pali
 	 		else
 	 			visit_number(low + 1, high, visit_number_pair(low, low, low * low, high, largest_pali))
	 	 
 	 	visit_number(low, high, 0)
 	 } 
   
 /*
  * Smallest multiple
	*	Project Euler Problem 5
	*
	* 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
	*
	* What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
  *
  */           
  def min_divisible(minDiv : Int, maxDiv : Int) : Int = {
    def is_divisible(n : Int, d : Int, maxDiv: Int) : Boolean =
      if (d > maxDiv) true else if (n % d != 0) false else is_divisible(n, d + 1, maxDiv)
 	
   	@tailrec
   	def min_divisible_tail(n: Int, minDiv : Int, maxDiv : Int) : Int =
   		if (is_divisible(n, minDiv, maxDiv)) n else min_divisible_tail(n+1, minDiv, maxDiv)
    
   	min_divisible_tail(maxDiv, minDiv, maxDiv) 
  }	 
  
  /*
	 * Sum square difference
	 * Project Euler Problem 6
	 * The sum of the squares of the first ten natural numbers is,
	 *
	 * 12 + 22 + ... + 102 = 385
	 * The square of the sum of the first ten natural numbers is,
	 *
	 * (1 + 2 + ... + 10)2 = 552 = 3025
	 * Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 − 385 = 2640.
	 *
	 * Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
	 */
	 def diff_sqsum_sumsq(n : Int) : Int = {
		 def square(x : Int) :Int = x * x
		 def id(x : Int) : Int = x
		 
		 def sum(n : Int, fn : Int => Int) : Int = {
		 	
		 	def sum_tail(acc: Int, n : Int, fn : Int => Int) : Int =
		 		if (n == 0) acc else sum_tail(acc + fn(n), n - 1, fn)
	   	
	   	sum_tail(0, n, fn)
	   	
		 }
		 
		 square(sum(n, id)) - sum(n, square)
	 } 
}