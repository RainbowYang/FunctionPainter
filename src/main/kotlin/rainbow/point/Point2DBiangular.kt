package rainbow.point

import rainbow.utils.Line

/**
 * 二维双角坐标点
 * @author Rainbow Yang
 */
class Point2DBiangular(val angle1: Double, val angle2: Double, val a: Double) : CoordinatePoint {

    operator fun component1() = angle1
    operator fun component2() = angle2

    override val asAxes: PointAxes
        get() = (Line(Point2D(a, 0), angle1) crossTo Line(Point2D(-a, 0), angle2)).asAxes

    override fun toString(): String {
        return "Point2DBiangular(angle1=$angle1, angle2=$angle2, a=$a)"
    }
    
}