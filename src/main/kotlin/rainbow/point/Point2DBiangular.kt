package rainbow.point

import rainbow.utils.Line

/**
 * 二维双角坐标点
 * @author Rainbow Yang
 */
class Point2DBiangular(val angle1: Double, val angle2: Double, val a: Double) : CoordinatePoint {

    override val asAxes by lazy { (Line(Point2D(a, 0), angle1) crossTo Line(Point2D(-a, 0), angle2)).asAxes }

    override fun toString() = "Point2DBiangular(angle1=$angle1, angle2=$angle2, a=$a)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Point2DBiangular

        if (angle1 != other.angle1) return false
        if (angle2 != other.angle2) return false
        if (a != other.a) return false

        return true
    }

    override fun hashCode(): Int {
        var result = angle1.hashCode()
        result = 31 * result + angle2.hashCode()
        result = 31 * result + a.hashCode()
        return result
    }

}