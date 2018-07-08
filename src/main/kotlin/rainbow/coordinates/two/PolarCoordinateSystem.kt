package rainbow.coordinates.two

import rainbow.coordinates.CoordinateSystem
import rainbow.point.Point2DPolar
import rainbow.point.PointAxes.Companion.ZERO
import rainbow.utils.CoordinateGraphics
import rainbow.utils.math.PI2

/**
 * 二维极坐标系
 *
 * @author Rainbow Yang
 */
class PolarCoordinateSystem : CoordinateSystem2D() {

    override var painter: CoordinateSystem.Painter<out CoordinateSystem> =
            object : CoordinateSystem.Painter<PolarCoordinateSystem>(this) {

                //todo 参数待动
                var paintRange = 0..30
                var axisNum = 12


                override fun paintOrigin(cg: CoordinateGraphics) =
                        cg.paintString("O")

                override fun paintGrid(cg: CoordinateGraphics) =
                        paintRange.forEach { cg.paintCircle(ZERO, Point2DPolar(it, 0)) }

                override fun paintAxes(cg: CoordinateGraphics) =
                        (0..axisNum).forEach { cg.paintRayLine(ZERO, Point2DPolar(10, PI2 / axisNum * it)) }

                override fun paintNumber(cg: CoordinateGraphics) =
                        paintRange.filter { it != 0 }.forEach { cg.paintString(it, ZERO.plusAtAndNew(0, it)) }

            }

}
