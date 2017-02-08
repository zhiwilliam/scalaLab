package com.zhiw.scala.oop

import scala.io.Source
object FileOps {
    def main(args:Array[String]):Unit= {
        val file = Source.fromFile("C:\\scala\\src\\workspace\\ScalaInAction\\src\\com\\zhiw\\scala\\oop\\FileOps.scala")
        for(line <- file.getLines()) print(line)
        file.close()
    }
}