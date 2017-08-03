package rainbow.coordinates

import java.awt.event.MouseEvent

/**
 * @author Rainbow Yang
 */
open class InputListenComponentOfCartesianCoordinateSystem(system: CartesianCoordinateSystem) :
        InputListenComponentOfCoordinateSystem2D(system) {

    var xAngle = 0.0
    var yAngle = 0.0

    var look: Pair<Number, Number>
        get() = xAngle to yAngle
        set(value) {
            xAngle = value.first.toDouble()
            yAngle = value.second.toDouble()
        }

    override fun mouseDragged(e: MouseEvent) {
        system as CartesianCoordinateSystem

        if (system.paintAsBall && firstEvent.button == MouseEvent.BUTTON2) {
            xAngle -= (e.x - lastEvent.x) / 10.0
            yAngle += (e.y - lastEvent.y) / 10.0

            system.axes.forEach { (it as BallAxis).resetAngleAndLength(xAngle, yAngle) }
        }

        super.mouseDragged(e)
    }
}