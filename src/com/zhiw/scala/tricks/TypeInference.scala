package com.zhiw.scala.tricks

// Scala type inference is local, flow-based.
object TypeInference extends App {
    def foo(a: Int, b: String) = a + b
    // The following defintion is wrong. Scala cannot find the definition of _. That's because it is local type inference
    // val f = foo(200, _)

    // you may define them as
    val f1: String => String = foo(200, _)
    // or
    val f2 = foo(200, _: String)
    // or
    def foo_eta_expasion(a: Int, b: String) = a + b
    val f3 = foo _
    val f4 = foo(_, _)

    class Animal { val sound = "rustle" }
    class Bird extends Animal { override val sound = "call" }
    class Chicken extends Bird { override val sound = "cluck" }
    val hatch: (() => Bird) = (() => new Chicken)
    // A function’s return value type is covariant.
    // So you can do val hatch: (() => Chicken) = (() => new Bird )

    /* Scala is a static type safe language. It applies Progress which means:A well typed (typable) program never gets "stuck".
     * The expressions in the program will either be evaluated to a "value", or there is a transition rule for it.
     * 
     * When we do String a = "some string"; in Java, Java looks at the left side and see String, looks at the right side and see a 
     * String type of value. Once left side type matches right side value type, it passes the evaluation.
     * In Scala, Scala introduced Type Inference which means it can inference your type out. Like val a = "some string" We don't need 
     * to tell Scala what "a" is because Scala can evaluate the right side as String then automatically assign String type to the left.
     *  
     * Now let's assume we have a function definition as method (a) = a, when compiler starts to evaluate it (static language means using
     * compiler to check type safe). It looks at the left side. What is type of a? Unknown. So it assume it is type T. What is the return 
     * type? Still undefined. It assume it to type U. Then it check the right side. The right side returns value a. So now the compiler 
     * knows return value type should be the same as the type of value a. Which is T. But what is T? Now the compiler be totally screwed. 
     * Then natually, T comes to the world. So now we can define the method as method[T](p:T) = p. To let compiler knows this type will be 
     * defined later. This can be called parameterized polymorphism.  
     */

    def method2[T](p: T) = println("ok") // method2 [T](p: T):Unit
    val f6 = method2 _ // f6: Any => Unit = <function1>      
    // method2 has T type input parameter, Function1's input is Contravariance
    // By Liskov Substitution principle, it will be replaced by upper bounds which is Any
    f6(12) // OK, because 12 is Int, subType of Any

    def method3[T](p: T) = p // method 3 [T](p: T):T
    val f7 = method3 _ // f7: Nothing => Nothing = <function1>
    // f7(12)                                 // Wrong, because 12 is Int, not Nothing
    // If input and return are both T. Then it will be replaced by Nothing.

    // high order function, takes lambda as input parameter.
    def hf(f: String => Unit) = f("higher")
    // Now we can use it like:
    hf(s => println(s)) // When we define function we already declared input parameter as String. So we can omit it.
    hf(println(_)) // All x=>g(x) can be written as place holder _
    hf(println _) // You may not be able to omit () when you call println. Only Object function parameter can omit
    hf(println) // scala eta-conversion.  x => f(x) can be written as f

    def fooo(a: Int, b: Int) = 0 // Eta Expansion can convert this to a value of type (Int, Int) => Int
    val f8 = fooo _ // So we can define a function omit the inputs. f8 will be expected has type (Int, Int) => Int
    val f9 = fooo(_, _) // Ok but you cannot define val f9 = fooo(_, 1) 
    // cause f3 will be expected as (x)=>fooo(a, 1) cannot be assigned as fooo.

    lazy val abc: _ => _ = ??? // Ignore it!

    // Type variance:
    // 1.
    trait Queue1[T] {}     // When type S is type A's subtype, Queue1[S] and Queue1[A] is different, same as Java
    // 2.
    trait Queue2[+T] {}    // When type S is type A's subtype, Queue2[S] is Queue2[A]'s subtype. Called Covariant
    // 3.
    trait Queue3[-T] {}    // When type S is type A's subtype, Queue3[A] is Queue3[S]'s subtype. Called Contravariant
    
    // Usage:
    trait Function1[-T, +U] {
        def apply(x: T): U   // So this method defines x must lower than T but upper than U.
    }
    
    def f10(x: Bird): Animal = x // instance of Function1[Bird, Animal] success because Bird is subtype of Animal
    //def f11(x: Animal): Bird = x // instance of Function1[Animal, Bird] failed x must lower then Animal but upper than Bird, not possible.
    
    // Type variables bounds:
    // Force the template parameter must has compareTo method.
    class Pair[T <: Comparable[T]](val first: T, val second: T) {
        def bigger = if (first.compareTo(second) > 0) first else second
    }
    val pair = new Pair("Spark", "Hadoop")
    println(pair.bigger)

    //A =:= B	A must be equal to B
    //A <:< B	A must be a subtype of B
    //A <%< B	A must be viewable as B
    def rocky[T](i: T)(implicit ev: T <:< java.io.Serializable) {}
    // rocky(pair) error!

    implicit def strToInt(x: String) = x.toInt // Try to comment this line out to see what happen
    class Container[A <% Int] { def addIt(x: A) = 123 + x }
    println((new Container[String]).addIt("123"))
    println((new Container[Int]).addIt(123))
    // (new Container[Float]).addIt(123.2F) Error definition.
}