package edu
import scala.annotation.tailrec
import scala.math.log
import scala.math.floor
import scala.math.pow
import scala.math.abs

class Rational(x: Int, y: Int) {

	// RPecedence rules:
	/**
	* The precedence of an operator is defined by its first character
	* low to high +-v
	*
	* (all letters)
	* |
	* ^
	* &
	* < >
	* = !
	* :
	* + -
	* * / %
	* (all other special characters)
	*
	*
	*
	*
	*/
		
	// Explicit Constructor
	def this(a : Int) = this(a, 1)
	
	
	// Implicit Constructor (Int, Int) => Rational
	require(y != 0, "denominator must be nonzero")
	
	private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
	private val g = gcd(abs(x), abs(y))
	
	def numer = x/g
	def denom = y/g
	
	// Class methods
	
	def add(that: Rational) =
		new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
	
	def +(that: Rational) = this add that
		
	def neg: Rational = new Rational(-numer, denom)
	
	def unary_- : Rational = this.neg
	
	def sub(that: Rational): Rational = this + -that
	
	def -(that: Rational): Rational = this sub that
	
	def reciprocal = new Rational(denom, numer)
	
	def mul(that: Rational) = new Rational(this.numer * that.numer, this.denom * that.denom)
	
	def * (that: Rational) = this mul that
	
	def div(that: Rational) = { require(that.numer != 0, "denominator operand cannot be zero") ; this * that.reciprocal }
	
	def less(that: Rational) : Boolean = (this.numer * that.denom) < (that.numer * this.denom)
	
	def < (that: Rational) : Boolean = less(that)
	
	def max(that: Rational) : Rational = if(this.less(that)) that else this
	
	override def toString() = numer + "/" + denom
	
}

object algo_ws {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
 	
 	val x = new Rational(1, 3)                //> x  : edu.Rational = 1/3
 	val y = new Rational(5, 7)                //> y  : edu.Rational = 5/7
 	val z = new Rational(3, 2)                //> z  : edu.Rational = 3/2
 	
 	val q = x - y -z                          //> q  : edu.Rational = -79/42
 	
 	x < y                                     //> res0: Boolean = true
 	
 	x max y                                   //> res1: edu.Rational = 5/7
 	
 	val r = new Rational(10, 50)              //> r  : edu.Rational = 1/5
 	
 	
}