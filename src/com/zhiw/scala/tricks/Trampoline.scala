package com.zhiw.scala.oop

sealed trait Bounce[A]
case class Done[A](result: A) extends Bounce[A]
case class Call[A](thunk: () => Bounce[A]) extends Bounce[A]

object Trampoline extends App {
    def trampoline[A](bounce: Bounce[A]): A = bounce match {
        case Call(thunk) => trampoline(thunk())
        case Done(x) => x
    }
    def even(n: Int): Bounce[Boolean] = {
        if (n == 0) Done(true)
        else Call(() => odd(n - 1))
    }
	
    def odd(n: Int): Bounce[Boolean] = {
        if (n == 0) Done(false)
        else Call(() => even(n - 1))
    }
    trampoline(even(9999))
}