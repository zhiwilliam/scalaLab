package com.zhiw.scala.design

class Singleton private {
    def f() = println("Hello Singleton");
}

object Singleton {
    private val singleton = new Singleton()
    def apply() = singleton
}

object SingletonPattern extends App {
    Singleton().f()
}