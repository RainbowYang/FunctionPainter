package rainbow.point

import rainbow.utils.asPoint2DHyperbolic
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

    override val asAxes get() = PointAxes(v * pow(E, u), v * pow(-E, u))

    override fun toString(): String {
        return "Point2DHyperbolic(u=$u, v=$v)"
    }

}