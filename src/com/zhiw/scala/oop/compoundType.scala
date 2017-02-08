package com.zhiw.scala.oop

trait A
trait B
class CompoundType extends A with B
object CompoundType extends App {
    def compoundType( x: A with B) {}
    val a = compoundType(new A with B)
    // Simple, you may with many traits to implement multiple inherit.
    
    // More details check Mixin
}