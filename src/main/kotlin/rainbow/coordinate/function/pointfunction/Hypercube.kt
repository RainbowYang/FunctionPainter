package rainbow.coordinate.function.pointfunction;

import rainbow.coordinate.function.CoordinateFunction
import rainbow.coordinate.function.CoordinateFunctionPainter
import rainbow.coordinate.graphics.CoordinateGraphics
import rainbow.coordinate.point.PointForAxes
import rainbow.coordinate.system.CoordinateSystem

/**
 * 任意维度的超方体
 * 基于paintLocation实现
 * 所以目前只能从原点出发
 * @author Rainbow Yang
 */
class Hypercube(var size: Int, var length: Double = 5.0) : CoordinateFunction() {
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
