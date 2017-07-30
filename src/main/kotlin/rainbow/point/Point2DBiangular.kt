package rainbow.point

import rainbow.utils.Line

/**
 * 二维双角坐标点
 * @author Rainbow Yang
 */
class Point2DBiangular(val angle1: Double, val angle2: Double, val a: Double) : Point2D {
    override fun toPointForAxes(): PointForAxes {
        val line1 = Line(PointDouble(a, 0), angle1)
        val line2 = Line(PointDouble(-a, 0), angle2)

        return line1.getCross(line2).toPointForAxes()
    }

    override fun times(times: Double): CoordinatePoint {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}