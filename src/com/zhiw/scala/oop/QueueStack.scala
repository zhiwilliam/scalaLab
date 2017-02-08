package com.zhiw.scala.oop

import scala.collection.mutable.Queue;

object QueueStack extends App {
    val empty = Queue[Int]()
    empty.enqueue(1)
    empty.enqueue(2)
    empty.enqueue(3)
    println(empty)
    
    var x, y, z = empty.dequeue()
    println(y)
    
    import scala.collection.mutable.Stack
    val stack = new Stack[Int]
    stack.push(2).push(4)
    println(stack.top)  // No top()
    println(stack)
    println(stack.pop)  // pop() is OK.
    println(stack)
}