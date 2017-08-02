package rainbow.point

import java.lang.Math.*

/**
 * 二维椭圆坐标点
 *
 * @author Rainbow Yang
 */
class Point2DElliptic(val μ: Double, val ν: Double, val a: Double) : CoordinatePoint {

    operator fun component1() = μ
    operator fun component2() = ν

    private val c1 get() = component1()
    private val c2 get() = component2()

    override val asAxes get() = PointAxes(a * cosh(c1) * cos(c2), a * sinh(c1) * sin(c2))

    override fun toString(): String {
        return "Point2DElliptic(μ=$μ, ν=$ν, a=$a)"
    }
}