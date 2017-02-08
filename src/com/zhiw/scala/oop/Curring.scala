package com.zhiw.scala.oop

object Curring extends App {
    def somefunc(x:Int, y:Int) = x + y
    println(somefunc(1, 2))
    def multipleOne(x:Int) = (y:Int) => x + y  
    println(multipleOne(1)(2))
    
    def curring(x: Int)(y: Int) = x * y
    println(curring(2)(3))
}