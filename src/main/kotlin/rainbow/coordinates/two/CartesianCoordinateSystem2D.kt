package rainbow.coordinates.two

import rainbow.coordinates.CoordinateSystem
import rainbow.point.Point2D
import rainbow.point.PointAxes
import rainbow.utils.CoordinateGraphics

/**
 * @author Rainbow Yang
 */
class CartesianCoordinateSystem2D : CoordinateSystem2D() {

    override var painter: Painter<out CoordinateSystem> =
            object : CoordinateSystem.Painter<CartesianCoordinateSystem2D>(this) {

                //todo 参数待动
                var paintRange = -30..30

                override fun paintOrigin(cg: CoordinateGraphics) = cg.paintString("O")

                override fun paintGrid(cg: CoordinateGraphics) =
                        paintRange.forEach {
                            cg.paintStraightLine(Point2D(it, 0), Point2D(it, 1))
                            cg.paintStraightLine(Point2D(0, it), Point2D(1, it))
                        }

                override fun paintAxes(cg: CoordinateGraphics) {
                    cg.paintStraightLine(PointAxes.ZERO, Point2D(1, 0))
                    cg.paintStraightLine(PointAxes.ZERO, Point2D(0, 1))
                }

                override fun paintNumber(cg: CoordinateGraphics) =
                        paintRange.filter { it != 0 }.forEach {
                            cg.paintString(it, PointAxes.ZERO.plusAtAndNew(0, it))
                            cg.paintString(it, PointAxes.ZERO.plusAtAndNew(1, it))
                        }

            }

}