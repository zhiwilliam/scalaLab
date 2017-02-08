package com.zhiw.scala.oop

class Outer {
    private val x = 10
    class Inner {
        private val y = x + 10
    }
}
object InnerOuter extends App {
    val outer = new Outer
    //val inner = new Outer.Inner
    val inner2: outer.Inner = new outer.Inner
    
    val o1 = new Outer
    val o2 = new Outer
    val i: Outer#Inner = new o1.Inner
}