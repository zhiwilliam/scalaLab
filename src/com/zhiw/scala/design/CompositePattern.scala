package com.zhiw.scala.design

// sealed means this class only be used in this module. And must provide all possible case classes.
sealed abstract class Component { def display }

case class Text(var text: String) extends Component {
    def display = println(text)
}
case class Picture(var picture: String) extends Component {
    def display = println(picture)
}
case class Composite(var children: List[Component]) extends Component {
    def display = children.foreach(x => x.display)
}

object CompositePattern extends App {
    val tree =
        Composite(List(Composite(List(Text("t1"), Picture("p1"))), Text("t2")))
    tree.display
    tree.children(1).display
}