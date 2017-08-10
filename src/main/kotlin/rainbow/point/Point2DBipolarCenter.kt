package rainbow.point

import java.lang.Math.*

/**
 * 二维双心坐标点
 * @author Rainbow Yang
 */
class Point2DBipolarCenter(val r1: Double, val r2: Double, val a: Double) : CoordinatePoint {

    operator fun component1() = r1
    operator fun component2() = r2

    override val asAxes by lazy {
        val d = (r1 * r1 - r2 * r2)
        val x = d / (4 * a)
        val y = sqrt(16 * a * a * r1 * r1 - pow((d + 4 * a * a), 2.0)) / (4 * a)
        PointAxes(x, y)
    }

    override fun toString()= "Point2DBipolarCenter(r1=$r1, r2=$r2, a=$a)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Point2DBipolarCenter

        if (r1 != other.r1) return false
        if (r2 != other.r2) return false
        if (a != other.a) return false

        return true
    }

    override fun hashCode(): Int {
        var result = r1.hashCode()
        result = 31 * result + r2.hashCode()
        result = 31 * result + a.hashCode()
        return result
    }

}