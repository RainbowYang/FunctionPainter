package rainbow.coordinate.function.pointfunction;

import rainbow.coordinate.function.CoordinateFunction
import rainbow.coordinate.function.CoordinateFunctionPainter
import rainbow.coordinate.graphics.CoordinateGraphics
import rainbow.coordinate.point.PointForAxes
import rainbow.coordinate.system.CoordinateSystem

/**
 * 任意维度的超方体
 * @author Rainbow Yang
 */
class Hypercube(var length: Double, var size: Int) : CoordinateFunction() {
    override var coordinateSystem: CoordinateSystem
        get() = super.coordinateSystem
        set(value) {
            painter = object : CoordinateFunctionPainter<Hypercube>(this, value) {
                override fun paintMain(cg: CoordinateGraphics) {
                    cg.paintLocation(PointForAxes(length, size))
                }
            }
        }
}
