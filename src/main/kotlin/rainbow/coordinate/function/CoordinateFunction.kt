package rainbow.coordinate.function

import rainbow.ables.EmptyPainter
import rainbow.ables.Paintable
import rainbow.ables.Painter
import rainbow.coordinate.CoordinatePainter
import rainbow.coordinate.graphics.CoordinateGraphics
import rainbow.coordinate.system.CoordinateSystem

/**
 * 所有能画在坐标系上的东西
 * @author Rainbow Yang
 */
abstract class CoordinateFunction : Paintable {
    open var coordinateSystem: CoordinateSystem
        get() = (painter as CoordinatePainter).coordinateSystem
        set(value) {}

    override var painter: Painter = EmptyPainter
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
