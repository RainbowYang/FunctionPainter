package rainbow.coordinate.system.cartesian

import rainbow.coordinate.system.InputListenerForCoordinateSystem2D
import java.awt.event.MouseEvent

/**
 * @author Rainbow Yang
 */
class InputListenerForCartesianCoordinateSystem(ccs: CartesianCoordinateSystem) : InputListenerForCoordinateSystem2D(ccs) {
    override fun mouseDragged(e: MouseEvent) {
        if (first.button == MouseEvent.BUTTON2) {
            val x = (e.x - last.x).toDouble() / 10
            val y = (e.y - last.y).toDouble() / 10
            ccs.axes.axes.forEach { it.move(x, -y) }
        }
        super.mouseDragged(e)
    }
}