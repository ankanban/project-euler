package edu

import edu.List

object oop_practice {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
	
	val t1 = new NonEmpty(3, Empty, Empty)    //> t1  : edu.NonEmpty = {.3.}
	val t2 = t1 incl 4                        //> t2  : edu.IntSet = {.3{.4.}}

	// Polymorphism
	def nth[T](n : Int, lst : List[T]): T =
		if (lst.isEmpty || n < 0)
			throw new IndexOutOfBoundsException("Invalid Index")
		else
				if (n == 0)
					lst.head
				else
					nth(n-1, lst.tail)
                                                  //> nth: [T](n: Int, lst: edu.List[T])T
						
	val lst1 = lfunc.singleton(1)             //> lst1  : edu.Cons[Int] = edu.Cons@7f63425a
	val lst2 = new Cons(1, new Cons(2, new Cons(3, new Nil)))
                                                  //> lst2  : edu.Cons[Int] = edu.Cons@36d64342
 	
 	nth(5, lst2)                              //> java.lang.IndexOutOfBoundsException: Invalid Index
                                                  //| 	at edu.oop_practice$$anonfun$main$1.nth$1(edu.oop_practice.scala:14)
                                                  //| 	at edu.oop_practice$$anonfun$main$1.apply$mcV$sp(edu.oop_practice.scala:
                                                  //| 24)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$$anonfun$$exe
                                                  //| cute$1.apply$mcV$sp(WorksheetSupport.scala:76)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.redirected(W
                                                  //| orksheetSupport.scala:65)
                                                  //| 	at org.scalaide.worksheet.runtime.library.WorksheetSupport$.$execute(Wor
                                                  //| ksheetSupport.scala:75)
                                                  //| 	at edu.oop_practice$.main(edu.oop_practice.scala:5)
                                                  //| 	at edu.oop_practice.main(edu.oop_practice.scala)
 	
	
}


// Class Hierarchies

abstract class IntSet {
	def incl(x: Int): IntSet
	def contains(x: Int): Boolean
	def union(other: IntSet): IntSet
}

object Empty extends IntSet {
	
	def contains(x : Int): Boolean = false
	
	def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
	
	override def toString = "."
	
	def union(other: IntSet): IntSet = other
	
}

class NonEmpty(elem : Int, left: IntSet, right: IntSet) extends IntSet {
	
	def contains(x: Int): Boolean = if (x < elem) left contains x else if ( x> elem) right contains x else true

	def incl(x: Int): IntSet = {
		// If x is less than this element
		if (x < elem)
			// Insert leftwards
			new NonEmpty(elem, left incl x, right)
		else
			// If x is greater than this element
			if (x > elem)
				// Insert rightwards
				new NonEmpty(elem, left, right incl x)
			else
				// The element exists in this node, return it
				this
	}
	
	def union(other: IntSet): IntSet = {
		((left union right) union other) incl elem
	}
	
	override def toString = "{" + left + elem + right + "}"
}

// Inheritance/Overriding:

abstract class Base {
	def foo = 1 // foo is defined
	def bar: Int // bar is only declared
}

class Sub extends Base {
	override def foo = 2 // Required as foo in Base class was already defined.
	def bar = 3 // Override is optional as bar was only decalred in Base.
}