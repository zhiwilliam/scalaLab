package com.zhiw.scala.oop

//covariant	C[T'] is C[T]'s subtype	[+T]
//contravariant	C[T] is C[T']'s sub type [-T]
//invariant	C[T] and C[T'] has no relationship	[T]

sealed class Covariant[+A]
sealed class Contravariant[-A]

sealed class Animal { val sound = "rustle" }
sealed class Bird extends Animal { override val sound = "call" }
sealed class Chicken extends Bird { override val sound = "cluck" }

object Variance extends App {
    val cv1: Covariant[AnyRef] = new Covariant[String] // AnyRef like Object in Java
    //val cv2: Covariant[String] = new Covariant[AnyRef] //Error
    val cv3: Contravariant[String] = new Contravariant[AnyRef]
    // val cv4: Contravariant[AnyRef] = new Contravariant[String] //Error

    // When do we use this stuff? See the definition of Function1
    trait Function1[-T, +U] {
        def apply(x: T): U
    }
    
    def f1(x: Bird): Animal = x  // instance of Function1[Bird, Animal]
    // def f2(x: Animal): Bird = x  // instance of Function1[Animal, Bird] 
    // In the second definition We found error because animal cannot be cast back to bird
    
    // def cacophony[T](things: Seq[T]) = things map (_.sound) // Error, sound not defined.
    def biophony[T <: Animal](things: Seq[T]) = things map (_.sound) // Notice <: called bounds.
    biophony(Seq(new Chicken, new Bird)) // It works.
    
    def hashcodes(l: Seq[_ <: AnyRef]) = l map (_.hashCode)
    // hashcodes(Seq(1,2,3)) // Error, type mismatch
    hashcodes(Seq("one", "two", "three"))
    
    //Check List sum method, it defined as sum[B >: A](implicit num: Numeric[B]): B
    println(List(1,3).sum)
    //println(List("he","dd").sum) // Error
}