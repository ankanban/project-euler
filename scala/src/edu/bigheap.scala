package edu
import java.util
import scala.annotation.tailrec

object bigheap extends App {

    abstract class Heap[+T] {
      def parent(i: Int) : Int
      def left(i: Int) : Int
      def right(i: Int) : Int
      
      def getData : T
      def get(i : Int) : Heap[T]
      def isEmpty : Boolean
      def getIndex : Int
    }
    
    case class Empty(index: Int) extends Heap {
      override def parent(i: Int) : Int = throw new java.util.NoSuchElementException()
      override def left(i: Int) : Int = throw new java.util.NoSuchElementException()
      override def right(i: Int) : Int = throw new java.util.NoSuchElementException()
      override def getIndex : Int = index
      override def getData = throw new java.util.NoSuchElementException()
      override def get(i : Int) = throw new java.util.NoSuchElementException()
      
      def isEmpty : Boolean = true
    }
    
    case class Node[T](val index : Int, 
                        val leftNode : Heap[T], 
                        val rightNode : Heap[T], 
                        val data: T) extends Heap[T] {
      
      def parent(i: Int) : Int = i/2
      def left(i: Int) : Int = 2*i
      def right(i: Int) : Int = 2*i + 1
      
      def isEmpty : Boolean = false
      def getData : T = data
      def getIndex : Int = index
      
      override def get(i: Int) : Heap[T] = {
        
        @tailrec
        def get_loop(queue: List[Heap[T]]) : Heap[T] = {
          if (queue.isEmpty) 
            throw new java.util.NoSuchElementException()
          else
            if (queue.head.isEmpty) {
              get_loop(queue.tail)
            } else {
              if (queue.head.getIndex == index) 
                queue.head 
              else 
                get_loop(leftNode :: rightNode :: queue.tail)
            } 
        }
        
        get_loop(List[Heap[T]](this))
      }
      
    }
    
    def maxHeapify[T](list:List[T], i: Int):Heap[T] = {

      def parent(i: Int) : Int = i/2
      def left(i: Int) : Int = 2*i
      def right(i: Int) : Int = 2*i + 1

      if (i >= list.length) 
        Empty(i)
      else 
        Node(i, maxHeapify(list, left(i)), maxHeapify(list, right(i)), list(i))
    }
    
    def buildMaxHeap[T](list:List[T]):Heap[T] = {
      maxHeapify(list, 0)
    }
    
    def heapMaximum[T](heap:Heap[T]): T = return heap.get(0).getData
    
    
}