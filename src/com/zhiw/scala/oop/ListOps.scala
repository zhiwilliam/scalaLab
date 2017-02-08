package com.zhiw.scala.oop
import scala.annotation.tailrec
object ListOps extends App {
    val bigData = List("Hadoop", "Spark", "Kaffka")
    val data = List(1, 2, 3)
    val bigData_Core = "Hadoop"::("Spark"::Nil)
    val data_Int = 1::2::3::4::Nil
    
    println(data.isEmpty)
    println(data.head)
    println(data.tail.head)
    
    val List(a,b,c) = bigData
    println("a: " + a + " === " + "b: " + b)
    val x::y::rest = data
    println("x: " + x + " === " + " y: " + y + " === " + rest)
    
    println(bigData)
    println(bigData.last)
    println(bigData.init)
    println(bigData.reverse)
    println(bigData.take(2))
    println(bigData.drop(2))
    println(bigData splitAt 2)
    println(bigData apply 2)
    println(bigData(2))
       
    println(bigData.indices)
    println(bigData.indices zip bigData)
    println(bigData.zipWithIndex)
    println(bigData.mkString("[",",","]"))
    println(bigData.mkString)
    
    val buffer = new StringBuilder
    bigData addString (buffer, "(", ";;", ")")
    println(buffer)
    
    val arr = bigData.toArray
    arr.foreach(println)
    
    println(List(21,23,46) map (_ + 1))
    println(List(1,2,3) reduce (_ + _))
    println(List(1,2,3) takeWhile (_ < 3))
    println(List(1,2,3) dropWhile (_ < 3))
    println(List(1,2,3) takeWhile (_ > 1)) // Doesn't make sense why?
    println(List(1,2,3, 4, 5) span (_ < 4))
    
    println((1 to 10).foldLeft(0)(_+_)) // start from 0, left add right
    println((0/:(1 to 10))(_+_))       // same as above written
    println((1 to 5).foldRight(100)(_-_))  // This _-_ gives you very strange result. Notice the next line, x and y reversed.
    println(((1 to 5):\100)((x,y)=>(y-x)))       // start from 100 minus the most right one, then right minus left
    
    // indicate exists and forall usage
    def hasZeroRow(m : List[List[Int]]) = m exists (row => row forall (_ == 0))
    println(hasZeroRow(List(List(1,5,6), List(2,3,4), List(0,0,0)))) // count )))) is really a difficult work.
    
    println(List(List(2,3),List(4,5)).flatten)
    
    val shuffledData = List(1,4,3,5,12,2)
    def sortList(list:List[Int]) : List[Int] = list match {
        case List() => List()
        case head::tail => compute(head, sortList(tail))
    }    
    // To do: optimize to tail recursive.
    // @tailrec
    def compute(data: Int, dataSet: List[Int]):List[Int] = dataSet match {
        case List() => List(data)
        case head::tail=> if(data <= head) data::compute(head, tail) else head::compute(data, tail)
    }
    println(sortList(shuffledData))
    def mergesort[T] (less:(T, T)=>Boolean)(input: List[T]): List[T] = {
        def merge(xList: List[T], yList: List[T]):List[T] = (xList, yList) match {
            case (Nil, Nil) => List()
            case (Nil, _) => yList
            case (_, Nil) => xList
            case (x :: xtail, y:: ytail) => if(less(x, y)) x::merge(xtail, yList) 
                                            else y::merge(xList, ytail)
        }
        val n = input.length / 2
        if(n == 0) input
        else {
            val(x, y) = input splitAt n  // split the input list into two
            merge(mergesort(less)(x), mergesort(less)(y))
        }
    }
    println(mergesort((x: Int, y:Int)=> x < y)(List(3,7,9,5)))
}