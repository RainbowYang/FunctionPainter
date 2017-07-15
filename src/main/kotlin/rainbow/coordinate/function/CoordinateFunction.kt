package rainbow.coordinate.function

import rainbow.coordinate.CoordinatePainter
import rainbow.coordinate.graphics.CoordinateGraphics
import rainbow.coordinate.system.CoordinateSystem
import rainbow.paint.EmptyPainter
import rainbow.paint.Paintable
import rainbow.paint.Painter

/**
 * 所有能画在坐标系上的东西
 * @author Rainbow Yang
 */
abstract class CoordinateFunction : Paintable {
    override var painter: Painter = EmptyPainter

    open var coordinateSystem: CoordinateSystem
        get() = (painter as CoordinatePainter).coordinateSystem
        set(value) {}

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
