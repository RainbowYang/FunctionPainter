package rainbow.point

import java.lang.Math.*

/**
 * 二维椭圆坐标点
 *
 * @author Rainbow Yang
 */
class Point2DElliptic(val μ: Double, val ν: Double, val a: Double) : Point2D {

    operator fun component1() = μ
    operator fun component2() = ν

    private val c1 get() = component1()
    private val c2 get() = component2()

    override fun toPointForAxes() = PointForAxes(a * cosh(c1) * cos(c2), a * sinh(c1) * sin(c2))

}