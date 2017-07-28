package rainbow.function

import rainbow.component.EmptyPainter
import rainbow.component.Paintable
import rainbow.component.Painter
import rainbow.coordinate.CoordinatePainter
import rainbow.utils.CoordinateGraphics
import rainbow.coordinates.CoordinateSystem

/**
 * 所有能画在坐标系上的东西
 * @author Rainbow Yang
 */
abstract class CoordinateFunction {
    open fun init() {}

}

abstract class CoordinateFunctionPainter<out F : CoordinateFunction>(val function: F, coordinateSystem: CoordinateSystem)
    : CoordinatePainter(coordinateSystem) {

    open fun paintBefore(cg: CoordinateGraphics) {}
    open fun paintMain(cg: CoordinateGraphics) {}
    open fun paintAfter(cg: CoordinateGraphics) {}

    override fun paintByCoordinateGraphics(cg: CoordinateGraphics) {
        paintBefore(cg)
        paintMain(cg)
        paintAfter(cg)
    }

}
