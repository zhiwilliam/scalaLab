package com.zhiw.scala.oop

// define template parameter must has compareTo method.
class Pair[T <: Comparable[T]](val first: T, val second: T) {
    def bigger = if (first.compareTo(second) > 0) first else second
}

object TypeVariablesBounds extends App {
    val pair = new Pair("Spark", "Hadoop")
    println(pair.bigger)

    //A =:= B	A must be equal to B
    //A <:< B	A must be a subtype of B
    //A <%< B	A must be viewable as B
    def rocky[T](i: T)(implicit ev: T <:< java.io.Serializable) {}
    // rocky(pair) error!
    
    implicit def strToInt(x: String) = x.toInt // Try to comment this line out to see what happen
    class Container[A <% Int] { def addIt(x: A) = 123 + x }
    println((new Container[String]).addIt("123"))
    println((new Container[Int]).addIt(123))
    // (new Container[Float]).addIt(123.2F) Error definition.
}

