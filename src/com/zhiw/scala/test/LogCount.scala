package com.zhiw.scala.test

import scala.io.Source
import scala.collection.mutable.Map
import java.text.SimpleDateFormat
import java.util.Date
import scala.annotation.tailrec
sealed trait Tree {
    val kids: Map[Byte, Tree] = Map[Byte, Tree]()
    def getTree(key: Byte) = kids.get(key)
    def getValue(): String;
    val formatter = new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss")

    final def putTree(keys: List[Byte], date: String): Unit = keys match {
        case head :: Nil => kids.get(head) match {
            case x: Option[Tree] => kids.put(head, Leaf(date)) // ignore the only in case
            case _               => kids.put(head, Leaf(date))
        }
        case Nil => Unit // Do nothing.
        case head :: tail => kids.get(head) match {
            case x: Option[Tree] => x.get.putTree(tail, date)
            case _ => {
                val tree = Node(head)
                kids.put(head, tree)
                tree.putTree(tail, date)
            }
        }
    }

    @tailrec
    final def getInterval(keys: List[Byte], date: String): Long = keys match {
        case Nil => -1L // Do nothing.
        case head :: Nil => kids.get(head) match {
            case x: Option[Tree] => (formatter.parse(date).getTime() - formatter.parse(x.get.getValue).getTime()) / 1000
            case _               => -1L
        }
        case head :: tail => kids.get(head) match {
            case None            => -1L
            case x: Option[Tree] => x.get.getInterval(tail, date)
        }

    }
}

sealed case class MyTree(key: Byte) extends Tree {
    def getValue() = ""
}
sealed case class Node(key: Byte) extends Tree {
    def getValue() = ""
}

sealed case class Leaf(value: String) extends Tree {
    private val time1: String = value
    def getValue() = time1
}








object LogCount extends App {
    val filename = """C:\DEV\scala\workspace\scalaLab\aws.log"""
    val patternIn = """ IN """.r
    val patternTime = """[0-1]\d\/(Jan(uary)?|Feb(ruary)?|Mar(ch)?|Apr(il)?|May|Jun(e)?|Jul(y)?|Aug(ust)?|Sep(tember)?|Oct(ober)?|Nov(ember)?|Dec(ember)?)\/\d{4}:[0-2][0-3]:[0-5][0-9]:[0-5][0-9]""".r
    val patternURL = """(GET|PUT|POST|DELETE)\s.*?""".r
    val patternIPv4 = """(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})""".r
    val formatter = new SimpleDateFormat("dd/MMM/yyyy:hh:mm:ss")
   
    implicit class TuppleAdd(t: (Long, Long)) {
        def +(p: (Long, Long)) = (p._1 + t._1, p._2 + t._2)
    }
    
    def intervalTime(v:List[(Option[String], Option[String], Option[String])]):(Long, Long) = v match {
        case (_, Some(" IN "), x)::(_, None, y)::tail => intervalTime(tail) + 
            (1, (formatter.parse(y.get).getTime() - formatter.parse(x.get).getTime())/1000)
        case (_, None, x)::(_, None, y)::tail => intervalTime(tail)
        case (_, Some(" IN "), x)::(z, Some(" IN "), y)::tail => intervalTime((z, Some(" IN "), y)::tail)
        case _ => (0, 0)
    }
    val mapcc = Source.fromFile(filename).getLines().
        map(line => (patternIPv4.findFirstIn(line), patternIn findFirstIn line, patternTime.findFirstIn(line))).
        toList.groupBy(_._1)

    for((k, v) <- mapcc) {
        val res = intervalTime(v)
        if( res._1 != 0)
        println(k + " " + res._2 /res._1) 
    }
    
    
    

    
    var tree: Tree = new MyTree(12)

    /*for (line <- Source.fromFile(filename).getLines()) {
        val in = patternIn findFirstIn line
        val timeOpt = patternTime findFirstIn line
        val url = patternURL findFirstIn line
        val date = timeOpt.get
        patternIPv4 findFirstIn line match {
            case None => None
            case Some(x) => {
                val keys = x.split(".").map(a => a.toByte)
                if (!in.isEmpty) {
                    tree.putTree(keys.toList, date)
                } else {
                    val t = tree.getInterval(keys.toList, date)
                    println(t)
                }
            }

        }
    }*/
    /*def reduceByKey[K](collection: Traversable[Tuple2[K, List[Option[String]]]]) = {
        collection
            .groupBy(_._1)
            .map { case (group: K, traversable) => traversable.reduce { (a, b) => (a._1, a._2 + b._2) } }
    }
    */
   

}