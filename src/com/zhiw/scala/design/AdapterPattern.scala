package com.zhiw.scala.design

class Adaptee {
    def g() = println("Do something")
}

trait Target {
    def f()
}

trait Adapter {
    self: Target with Adaptee => 
        def f = g 
}

object AdapterPattern extends App {
    val adapter = new Adaptee with Adapter with Target
    adapter.f // So we don't need to modify target class but can use the function by calling f
    adapter.g
}