package com.zhiw.scala.oop

object RegExpressOps {
    def main(args: Array[String]) {
        val regex="""([0-9]+) ([a-z]+)""".r
        val numPattern = "[0-9]+".r
        val numberPattern = """\s+[0-9]+\s+""".r
        
        // findAllIn returns all match strings. So using for to iterate it.
        for (matchString <- numPattern.findAllIn("99345 Scala, 22298 Spark")) println(matchString)
        
        println(numberPattern.findFirstIn("99ss java, 222 hadoop")) // Some(222)
        
        val numItemPattern="""([0-9]+) ([a-z]+)""".r
        
        val numItemPattern(num, item) = "99 hadoop"
        
        val line = "93459 spark"
        line match {
            case numItemPattern(num, blog) => println(num + "\t" + blog)
            case _ => println("Oh")
        }
    }
}