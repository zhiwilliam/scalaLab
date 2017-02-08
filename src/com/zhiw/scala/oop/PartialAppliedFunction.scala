package com.zhiw.scala.oop

object PartialAppliedFunction extends App {
    val data = List(1,2,3,4,5,6)
    data.foreach(x=>print(x))
    println("")
    data.foreach(print _) // Oh, you can do this...
    println("")
    
    def sum(a :Int, b:Int, c:Int) = a + b + c   // here using = sign to define function in one line.
    
    println(sum (1,2,3))
    
    val fp_a = sum _   // define fp_a with sum and all parameters. Don't forget underscore.
    println(fp_a)
    println(fp_a(1,2,3)) // same as fp_a.apply(1, 2, 3)
    val fp_b = fp_a(1, _:Int, 3) // partial derivative.
    print(fp_b(9))
}