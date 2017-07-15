package rainbow.coordinate.system

import rainbow.coordinate.CoordinatePainter
import rainbow.coordinate.graphics.CoordinateGraphics

/**
 * 所有CoordinateSystemPainter的接口
 * @author Rainbow Yang
 */
abstract class CoordinateSystemPainter(coordinateSystem: CoordinateSystem) : CoordinatePainter(coordinateSystem) {

    open fun paintOrigin(cg: CoordinateGraphics) {}
    open fun paintGrid(cg: CoordinateGraphics) {}
    open fun paintAxes(cg: CoordinateGraphics) {}
    open fun paintNumber(cg: CoordinateGraphics) {}

    override fun paintByCoordinateGraphics(cg: CoordinateGraphics) {
        paintOrigin(cg)
        paintGrid(cg)
        paintAxes(cg)
        paintNumber(cg)
    }
}
