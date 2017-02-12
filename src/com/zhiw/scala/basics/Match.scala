package com.zhiw.scala.oop

abstract class Person
case class Student(age: Int) extends Person
case class Worker(age: Int, salary: Double) extends Person
case object Shared extends Person

abstract class Item
case class Book(description: String, price: Double) extends Item
case class Bundle(description: String, price: Double, items: Item*) extends Item

object Match extends App {
    def matchList( lst: Any ) = lst match {
        case 0::Nil => println("Only 0 in list")
        case x::y::Nil => println("Only 2 items in list")
        case 1::_ => println("First one is 1")
        case 0::tail => println("List starts from 0")
        case _ => println("Unhandled list")
    }
    
    def matchArray(arr:Any) = arr match {
        case Array(0, _) => println("Two items array starts from 0")
        case Array(x, y) => println("two items array")
        case Array(0, _*) => println("Array starts from 0")
        case _ => println("No handle")
    }
    
    matchList(List(0,1))  // The early case will capture it and don't send it out again.
    matchList(List(0,1,2))
    matchList(List(1, 2, 3))
    
    matchArray(Array(0,1))
    matchArray(Array(0,1,2))
    matchArray(Array(1,4))
    
    println("--------------------------------------------------------------------------------")
    
    def caseOps(person: Person) = person match {
        case Student(age)=>println("Student's age is " + age)
        case Worker(_, salary)=>println("Wow, it's " + salary)
        case Shared => println("No properties")
    }
    val worker = Worker(29, 10000.1)
    val worker2 = worker.copy(salary = 19.94)
    val worker3 = worker.copy(age = 30)
    caseOps(Student(20))
    caseOps(worker)
    caseOps(worker2)
    caseOps(worker3)
    
    println("--------------------------------------------------------------------------------")
    
    def caseclass_nested(person: Item): Unit = person match {
        // if found Book object, using art to fetch it.
        case Bundle(_,_, art@ Book(_,_), rest@ _*) => {
            println(art.description + ":" + art.price)
            for(bundle <- rest) caseclass_nested(bundle) 
        }
        //case Bundle(_,_, Book(desc,_), _*) => println(desc)
        case Book(desc, price) => {
            println(desc + ":" + price)
        }
        case _ => println("Oops")
    }
    caseclass_nested( Bundle("1111 Special's", 90.0, Book("Hadoop", 40.0), 
            Bundle("Big Data", 50.0, Book("Hive", 79.74), Book("Fifi", 203.99)),
            Bundle("Try", 40.0, Book("Test", 22.1))))
    
    println("--------------------------------------------------------------------------------")
    
    val scores = Map("Alice"-> 99, "Spark" -> 100)
    scores.get("Aliced") match {
        case Some(score)=> println(score)
        case None => println("none")
    }
}