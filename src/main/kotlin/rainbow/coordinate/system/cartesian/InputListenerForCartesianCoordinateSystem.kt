package rainbow.coordinate.system.cartesian

import rainbow.coordinate.system.InputListenerForCoordinateSystem2D
import java.awt.event.MouseEvent

/**
 * @author Rainbow Yang
 */
class InputListenerForCartesianCoordinateSystem(ccs: CartesianCoordinateSystem) : InputListenerForCoordinateSystem2D(ccs) {
    override fun mouseDragged(e: MouseEvent) {
        super.mouseDragged(e)
    }
}