package rainbow.coordinate.system

import rainbow.coordinate.graphics.CoordinateGraphics
import rainbow.paint.Painter
import java.awt.Graphics

/**
 * 所有CoordinateSystemPainter的接口
 * @author Rainbow Yang
 */
abstract class CoordinateSystemPainter(val coordinateSystem: CoordinateSystem) : Painter {
    open fun paintOrigin(mg: CoordinateGraphics) {}
    open fun paintGrid(mg: CoordinateGraphics) {}
    open fun paintAxes(mg: CoordinateGraphics) {}
    open fun paintNumber(mg: CoordinateGraphics) {}

    override fun paint(g: Graphics, width: Double, height: Double) {
        val mg = CoordinateGraphics(g, coordinateSystem, width, height)
        paintOrigin(mg)
        paintGrid(mg)
        paintAxes(mg)
        paintNumber(mg)
    }
}
