package rainbow.point

import rainbow.utils.component1
import rainbow.utils.component2
import java.lang.Math.*

/**
 * 二维双曲坐标点
 * @author Rainbow Yang
 */
class Point2DHyperbolic(val u: Double, val v: Double) : Point2D {

    operator fun component1() = u
    operator fun component2() = v

    companion object {
        operator fun invoke(from: PointForAxes): Point2DHyperbolic {
            val (x, y) = from
            return Point2DHyperbolic(-0.5 * log(y / x), sqrt(x * y))
        }
    }

    override fun toPointForAxes() = PointForAxes(v * pow(E, u), v * pow(-E, u))
}