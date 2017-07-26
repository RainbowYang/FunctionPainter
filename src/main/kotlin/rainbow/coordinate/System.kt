package rainbow.coordinate

import rainbow.ables.EmptyPainter
import rainbow.ables.Paintable
import rainbow.ables.Painter
import rainbow.coordinate.function.CoordinateFunction
import rainbow.coordinate.function.CoordinateFunctions
import rainbow.coordinate.system.CoordinateSystem
import java.awt.Graphics

/**
 * 本类用于表示[CoordinateSystem]和List<[CoordinateFunction]>
 * @author Rainbow Yang
 */
class System(var coordinateSystem: CoordinateSystem,
             var coordinateFunctions: CoordinateFunctions = CoordinateFunctions()
) : Paintable {
    override var painter: Painter = EmptyPainter

    override fun paintImage(g: Graphics) {
        coordinateSystem.paintImage(g)
        coordinateFunctions.forEach { it.paintImage(g) }
    }

    override fun repaint(width: Number, height: Number, callback: () -> Unit) {
        coordinateSystem.repaint(width, height, callback)
        coordinateFunctions.forEach { it.repaint(width, height, callback) }
    }

}