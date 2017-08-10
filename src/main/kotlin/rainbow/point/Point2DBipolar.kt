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
    override val asAxes by lazy {
        val base = a / (cosh(c2) - cos(c1))
        PointAxes(sinh(c2) * base, sin(c1) * base)
    }

    override fun toString()="Point2DBipolar(σ=$σ, τ=$τ, a=$a)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Point2DBipolar

        if (σ != other.σ) return false
        if (τ != other.τ) return false
        if (a != other.a) return false

        return true
    }

    override fun hashCode(): Int {
        var result = σ.hashCode()
        result = 31 * result + τ.hashCode()
        result = 31 * result + a.hashCode()
        return result
    }

}
