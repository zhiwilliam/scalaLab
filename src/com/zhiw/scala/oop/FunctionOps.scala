package com.zhiw.scala.oop

object FunctionOps extends App {
    var increase = (x: Int) => x + 1
    println(increase)
    val res = increase (10)
    println(res)
    increase = (x: Int) => x + 9999
    println(increase)
    
    val someNums = List(-11, -10, -5, 0, 5, 10)
    someNums.foreach((x:Int)=>print(x + " "))
    println("")
    // The following written lines are identical functionality. Just try to learn the style.
    var newList = someNums.filter((x:Int)=>x>0)
    println(newList)
    newList = someNums.filter((x)=>x>0)
    println(newList)
    newList = someNums.filter(x=>x>0)
    println(newList)
    newList = someNums.filter(_>0)
    println(newList)
    
    val f = (_:Int)+ (_:Int) 
    print(f)      // Same as tuple type, the max paramter number is limited to 22
    print(f(5,4)) // 9
    
   
}