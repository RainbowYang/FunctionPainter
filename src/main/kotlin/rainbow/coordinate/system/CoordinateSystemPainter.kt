package rainbow.coordinate.system

import rainbow.coordinate.graphics.MathGraphics
import rainbow.paint.Painter
import java.awt.Graphics

/**
 * 所有CoordinateSystemPainter的接口
 * @author Rainbow Yang
 */
abstract class CoordinateSystemPainter(val coordinateSystem: CoordinateSystem) : Painter {
    open fun paintOrigin(mg: MathGraphics) {}
    open fun paintGrid(mg: MathGraphics) {}
    open fun paintAxes(mg: MathGraphics) {}
    open fun paintNum(mg: MathGraphics) {}

    override fun paint(g: Graphics, width: Double, height: Double) {
        val mg = MathGraphics(g, coordinateSystem)
        paintOrigin(mg)
        paintGrid(mg)
        paintAxes(mg)
        paintNum(mg)
    }
}
