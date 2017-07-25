package rainbow.coordinate.system

import rainbow.ables.InputListener
import rainbow.coordinate.system.cartesian.CartesianCoordinateSystem
import rainbow.util.getDiffAngle
import java.awt.event.MouseEvent
import java.awt.event.MouseEvent.BUTTON1
import java.awt.event.MouseEvent.BUTTON3
import java.awt.event.MouseWheelEvent

/**
 * @author Rainbow Yang
 */

open class InputListenerForCoordinateSystem2D(val ccs: CartesianCoordinateSystem) : InputListener() {
    lateinit var first: MouseEvent
    lateinit var last: MouseEvent

    override fun mousePressed(e: MouseEvent) {
        first = e
        last = e
    }

    //坐标系移动
    override fun mouseDragged(e: MouseEvent) {
        when (first.button) {
            BUTTON1 -> ccs.move(e.x - last.x, e.y - last.y)
            BUTTON3 -> ccs.rotate(ccs.getDiffAngle(last, e))
        }
        callback()
        last = e
    }

    override fun mouseWheelMoved(e: MouseWheelEvent) {
        ccs.zoom(Math.pow(1.1, e.wheelRotation.toDouble()))
        callback()
    }
}
