package com.zhiw.scala.design

// common interface for all implementors
trait WindowImp {
    def drawLine(x: Int, y: Int)
}

trait Window {
    self: WindowImp =>
        def drawRect(x1: Int, x2: Int, x3: Int, x4: Int) = {
            this.drawLine(x1, x2)
            this.drawLine(x1, x3)
            this.drawLine(x2, x4)
            this.drawLine(x3, x4)
        }
}
// abstractions
trait TransientWindow {
    self: Window => def drawCloseBox = drawRect(4, 3, 2, 1)
}
trait IconWindow {
    self: Window => def drawBorder = drawRect(1, 2, 3, 4)
}

// implementors
trait WindowOSX extends WindowImp {
    def drawLine(x: Int, y: Int) = println("drawing line in OSX")
}
trait WindowVista extends WindowImp {
    def drawLine(x: Int, y: Int) = println("drawing line in Vista ")
}

object BridgePattern extends App {
    val windowOSX: Window = new Window with WindowOSX
    windowOSX.drawRect(1, 2, 3, 4)
    
    val windowVista: Window = new Window with WindowVista
    windowVista.drawRect(1, 2, 3, 4)
}