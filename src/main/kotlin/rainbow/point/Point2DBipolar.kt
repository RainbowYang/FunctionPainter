package rainbow.point

import java.lang.Math.*

/**
 * 二维双极坐标点
 * @author Rainbow Yang
 */
class Point2DBipolar(val σ: Double, val τ: Double, val a: Double) : Point2D {

    operator fun component1() = σ
    operator fun component2() = τ
    val c1 get() = σ
    val c2 get() = τ

    override fun toPointForAxes(): PointForAxes {
        val base = a / (cosh(c2) - cos(c1))
        return PointForAxes(sinh(c2) * base, sin(c1) * base)
    }

    override fun times(times: Double): CoordinatePoint {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}