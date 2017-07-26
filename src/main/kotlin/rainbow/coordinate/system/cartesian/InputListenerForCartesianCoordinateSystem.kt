package rainbow.coordinate.system.cartesian

import rainbow.coordinate.point.PointDouble
import rainbow.coordinate.system.InputListenerForCoordinateSystem2D
import java.awt.event.MouseEvent

/**
 * @author Rainbow Yang
 */
class InputListenerForCartesianCoordinateSystem(ccs: CartesianCoordinateSystem) : InputListenerForCoordinateSystem2D(ccs) {
    override fun mouseDragged(e: MouseEvent) {
        if (first.button == MouseEvent.BUTTON2) {
            val x = (e.x - last.x).toDouble()
            val y = -(e.y - last.y).toDouble()

            val pd = PointDouble(x, y).spin(ccs.rotatedAngle)
            ccs.axes.moveSight(-pd.x / 10, -pd.y / 10)
        }
        super.mouseDragged(e)
    }
}