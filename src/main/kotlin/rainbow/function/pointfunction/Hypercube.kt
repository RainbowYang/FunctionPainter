package rainbow.function.pointfunction;

import rainbow.function.CoordinateFunction
import rainbow.point.PointForAxes
import rainbow.utils.CoordinateGraphics

/**
 * 任意维度的超方体
 * 基于paintLocation实现
 * 所以目前只能从原点出发
 * @author Rainbow Yang
 */
class Hypercube(var size: Int, var length: Double = 5.0) : CoordinateFunction() {

    override var paintComponent: CoordinateFunctionPainter = object : CoordinateFunctionPainter() {
        override fun paintMain(cg: CoordinateGraphics) {
            cg.paintLocation(PointForAxes(length, size))
        }
    }
}
