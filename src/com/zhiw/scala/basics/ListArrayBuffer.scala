package com.zhiw.scala.oop

import scala.collection.mutable.ListBuffer
import scala.collection.mutable.ArrayBuffer

object ListArrayBuffer extends App {
    val listBuffer = new ListBuffer[Int]
    listBuffer += 1
    listBuffer += 2
    println(listBuffer)
    
    val arrayBuffer = new ArrayBuffer[Int]
    arrayBuffer += 1
    arrayBuffer += 2
    println(arrayBuffer)
}