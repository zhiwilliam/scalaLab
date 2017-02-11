package com.zhiw.scala.oop

// You may define three different kind of items in package: case class, object(singleton) or trait
// package object can inject values or methods directly to a package.
// Attention: package object name must match target package name.
package object people {
    val defaultName = "Scala"
}

package people {
    class People {
        var name = defaultName // this value was injected.
        def printName() {
            System.out.println(name);
        }
    }
}

import System.out._
package society {
   package professional {
      class Executive {
         private[professional] var workDetails = "Description" //Modify the definition to upper level
         private[society] var friends = "Fafa and lala"
         private[this] var secrets = "secret"

         def help(another : Executive) {
            println(another.workDetails)
            println(another.friends)
            //println(another.secrets) //ERROR 
            // Variable secrets will be accessible only on the implicit object within instance methods (this)
         }
      }
   }
}

import people._                            // import everything in the package.
object PackageOps {
    def main(args: Array[String]):Unit = {
        val people = new People();
            people.printName();
    }
}

import java.awt.{Color, Font}               // import only a few items.
import java.util.{HashMap=>JavaHashMap}     // rename imported item.
import java.lang.StringBuilder
import scala.{StringBuilder=>_}           // hide the item in the import

class PackageOps {
    val build = new StringBuilder()
    val map = new JavaHashMap()
}

//There are three implicit import, means automatically imported for you.
import java.lang._
import scala._
import Predef._
