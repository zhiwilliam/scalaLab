package com.zhiw.scala.design

trait Component2 {
    def display
}
class EncapsulateTextView(c: TextView) extends Component2 {
    def display = c.display
}
class TextView(var s: String) extends Component2 {
    def display = println("displaying.. " + s)
}

// using trait to implement decorator
trait BorderDecorator extends Component2 {
    abstract override def display = { super.display; displayBorder }
    def displayBorder = println("displaying border ")
}
trait ScrollDecorator extends Component2 {
    abstract override def display = { scrollTo; super.display }
    def scrollTo = println("Scrolling .. ")
}

object DecoratorPattern extends App{
    val tw = new TextView(" foo ")
    val etw1 = new EncapsulateTextView(tw) with BorderDecorator with ScrollDecorator
    etw1.display
    tw.s = "bar"
    val etw2 = new EncapsulateTextView(tw) with ScrollDecorator with BorderDecorator
    etw2.display
}