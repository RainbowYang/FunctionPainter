package rainbow.coordinates

import rainbow.component.CoordinateTransformComponent
import rainbow.point.CoordinatePoint
import rainbow.point.Point2D
import rainbow.point.Point2DPolar
import rainbow.point.PointAxes.Companion.ZERO
import rainbow.utils.CoordinateGraphics
import rainbow.utils.PI2
import rainbow.utils.asPoint2D

/**
 * 二维的极坐标系
 *
 * @author Rainbow Yang
 */
class PolarCoordinateSystem : CoordinateSystem2D() {

    override var coordinateTransformComponent: CoordinateTransformComponent
            = CoordinateTransformComponentForPolarCoordinateSystem(this)

    override var paintComponent: CoordinateSystemPainter
            = PainterForPolarCoordinateSystem(this)

    //单位长度的像素数
    var axisLength = 40.0

    class CoordinateTransformComponentForPolarCoordinateSystem(
            val system: PolarCoordinateSystem
    ) : CoordinateTransformComponent() {

        override fun toScreenPoint(cp: CoordinatePoint) = with(system) {
            (cp.asPoint2D * system.axisLength).asPoint2D.rotateAndScaleAndMove()
        }


        override fun toCoordinatePoint(pd: Point2D) = with(system) {
            (pd.inverseRotateAndScaleAndMove() / axisLength).asAxes
        }
    }

    class PainterForPolarCoordinateSystem(val system: PolarCoordinateSystem) : CoordinateSystemPainter(system) {

        var paintRange = 0..30
        var axisNum = 12

        override fun paintOrigin(cg: CoordinateGraphics) {
            cg.paintString("0")
        }

        override fun paintGrid(cg: CoordinateGraphics) {
            paintRange.forEach {
                cg.paintCircle(ZERO, Point2DPolar(it, 0))
            }
        }

        override fun paintAxes(cg: CoordinateGraphics) {
            (0..axisNum).forEach {
                cg.paintRayLine(ZERO, Point2DPolar(10, PI2 / axisNum * it))
            }
        }

        override fun paintNumber(cg: CoordinateGraphics) {
            paintRange.filter { it != 0 }.forEach {
                cg.paintString(it, ZERO.plusAtAndNew(0, it))
            }
        }
    }
}