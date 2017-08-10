package rainbow.point

import rainbow.utils.component1
import rainbow.utils.component2
import java.lang.Math.*

/**
 * 二维双曲坐标点
 * @author Rainbow Yang
 */
class Point2DHyperbolic(val u: Double, val v: Double) : CoordinatePoint {

    operator fun component1() = u
    operator fun component2() = v

    companion object {
        operator fun invoke(from: PointAxes): Point2DHyperbolic {
            val (x, y) = from
            return Point2DHyperbolic(-0.5 * log(y / x), sqrt(x * y))
        }
    }

    override val asAxes by lazy { PointAxes(v * pow(E, u), v * pow(-E, u)) }

    override fun toString()= "Point2DHyperbolic(u=$u, v=$v)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Point2DHyperbolic

        if (u != other.u) return false
        if (v != other.v) return false

        return true
    }

    override fun hashCode(): Int {
        var result = u.hashCode()
        result = 31 * result + v.hashCode()
        return result
    }

}