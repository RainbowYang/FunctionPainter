package rainbow.coordinate.system.cartesian

import rainbow.ables.InputListener
import java.awt.event.MouseEvent
import java.awt.event.MouseEvent.BUTTON1
import java.awt.event.MouseWheelEvent

/**
 * @author Rainbow Yang
 */

class InputListenerForCartesianCoordinateSystem(val ccs: CartesianCoordinateSystem) : InputListener() {
    lateinit var first: MouseEvent
    lateinit var last: MouseEvent

    override fun mousePressed(e: MouseEvent) {
        first = e
        last = e
    }

    //坐标系移动
    override fun mouseDragged(e: MouseEvent) {
        when (first.button) {
            BUTTON1 -> {
                ccs.move(e.x - last.x, e.y - last.y)
                callback()
            }
        }
        last = e
    }

    //缩放效果
    override fun mouseWheelMoved(e: MouseWheelEvent) {
        ccs.zoom(Math.pow(1.1, e.wheelRotation.toDouble()))
        callback()
    }
}
