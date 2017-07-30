package rainbow.point

import rainbow.utils.Line

/**
 * 二维双角坐标点
 * @author Rainbow Yang
 */
class Point2DBiangular(val angle1: Double, val angle2: Double, val a: Double) : Point2D {

    operator fun component1() = angle1
    operator fun component2() = angle2

    override fun toPointForAxes(): PointForAxes {
        val line1 = Line(PointDouble(a, 0), angle1)
        val line2 = Line(PointDouble(-a, 0), angle2)

        return (line1 crossTo line2).toPointForAxes()
    }
}