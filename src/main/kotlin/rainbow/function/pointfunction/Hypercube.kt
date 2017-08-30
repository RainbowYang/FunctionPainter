package rainbow.function.pointfunction;

import rainbow.function.CoordinateFunction
import rainbow.point.PointAxes
import rainbow.utils.CoordinateGraphics

/**
 * 任意维度的超方体
 * 基于paintLocation实现
 * 所以目前只能从原点出发
 * @author Rainbow Yang
 */
class Hypercube(var size: Int, var length: Double = 5.0) : CoordinateFunction() {

    override var painter: Painter = HypercubePainter()

    inner class HypercubePainter : CoordinateFunction.Painter() {
        override fun paintMain(cg: CoordinateGraphics) {
            cg.paintLocation(PointAxes(length, size))
        }
    }
}
