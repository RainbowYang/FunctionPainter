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

    private val c1 get() = μ
    private val c2 get() = ν

    override val asAxes by lazy { PointAxes(a * cosh(c1) * cos(c2), a * sinh(c1) * sin(c2)) }

    override fun toString()= "Point2DElliptic(μ=$μ, ν=$ν, a=$a)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Point2DElliptic

        if (μ != other.μ) return false
        if (ν != other.ν) return false
        if (a != other.a) return false

        return true
    }

    override fun hashCode(): Int {
        var result = μ.hashCode()
        result = 31 * result + ν.hashCode()
        result = 31 * result + a.hashCode()
        return result
    }

}