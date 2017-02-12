package com.zhiw.scala.oop

trait wzhi {
    def foo(msg: String) = println(msg)
}

trait mama extends wzhi {
    val str1 = "mama: "
    // For the following definition, only the most right with item will be executed.
    //override def foo(msg: String) = println(str1.concat(msg))
    
    // For this line, super is a lazy function, it only executed when it has to be executed.
    override def foo(msg: String) = super.foo(str1.concat(msg))
}

trait papa extends wzhi {
    val str2 = "papa: "
    // For the following definition, only the most right with item will be executed.
    //override def foo(msg: String) = println(str2 + msg)
    
    // For this line, super is a lazy function, it only executed when it has to be executed (println).
    override def foo(msg: String) = super.foo(str2 + msg)
}

class Mixin private (msg: String) extends wzhi {
    def this() = this("mixin")
}

object Mixin extends App {
    // papa is actually mama's super in this case. 
    def apply(msg: String) = new Mixin(msg) with papa with mama
    val mixin = Mixin("jijiang")
    mixin.foo("wzhi: ")
}