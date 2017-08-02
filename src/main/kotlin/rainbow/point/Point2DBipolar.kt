package rainbow.point

import java.lang.Math.*

/**
 * 二维双极坐标点
 * @author Rainbow Yang
 */
class Point2DBipolar(val σ: Double, val τ: Double, val a: Double) : CoordinatePoint {

    operator fun component1() = σ
    operator fun component2() = τ

    private val c1 get() = σ
    private val c2 get() = τ

    override val asAxes: PointAxes
        get() {
            val base = a / (cosh(c2) - cos(c1))
            return PointAxes(sinh(c2) * base, sin(c1) * base)
        }

}