package com.zhiw.scala.oop

object Closure extends App {
    def add(more:Int) = (x:Int)=> x + more
    val addTen = add(10)
    println(addTen(5))
    // closure, function with outside parameter values. Some example shows below:
    var factor = 3
    val multiplier = (i:Int) => i * factor
    println(multiplier(5))
    // Well, I believe the first one makes more sense. Because we can "pack" 
    // a closure as partial derivative function. 
}