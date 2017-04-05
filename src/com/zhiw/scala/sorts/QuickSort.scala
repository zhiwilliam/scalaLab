package com.zhiw.scala.sorts

object QuickSort extends App {
    def sort(ls: List[Int]): List[Int] = {
        ls match {
            case Nil => Nil
            case pivot :: tail => {
                val (less, greater) = tail.partition(_ < pivot)
                sort(less) ::: pivot :: sort(greater)
            }
        }
    }
}