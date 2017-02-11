package com.zhiw.scala.oop

object SetMap extends App {
    import scala.collection.mutable.Set
    val data = Set.empty[Int]
    data ++= List(1,2,3)
    data += 2
    data --= List(2,3)
    println(data)
    data.clear
    
    import scala.collection.mutable.Map
    val map = Map[String, String]()
    map("Java") = "Hadoop"
    map.put("ss", "dd")
    println(map)
    
    import scala.collection.mutable.TreeSet
    val treeSet = TreeSet(1,3,4,33,5,6,8)
    println(treeSet)
    
    
}