package com.zhiw.scala.oop

sealed class Structural {
    def open() = println("A class instance Opened")
}

object Structural extends App {
    // This definition requires a type instance who has a method open be defined.
    def init( res: {def open():Unit}) { 
        res.open
    }
    init( new {def open()=println("No name type")})
    
    // You may define it in this way
    type X = {def open():Unit}
    def init2(res:X) = res.open()
    object A { def open() { println("Another choice")}}
    init2(A)
}