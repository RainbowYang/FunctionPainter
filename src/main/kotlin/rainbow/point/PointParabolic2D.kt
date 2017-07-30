package rainbow.point

import rainbow.utils.component1
import rainbow.utils.component2
import java.lang.Math.sqrt

/**
 * 二维抛物线坐标点
 *
 * @author Rainbow Yang
 */
class PointParabolic2D(val σ: Double, val τ: Double) : CoordinatePoint {
    operator fun component1() = σ
    operator fun component2() = τ
    val c1 get() = σ
    val c2 get() = τ

    companion object {
        operator fun invoke(form: PointForAxes): PointParabolic2D {
            val (x, y) = form
            return PointParabolic2D(sqrt(-y + sqrt(x * x + y * y)), sqrt(y + sqrt(x * x + y * y)))
        }
    }

    override fun toPointForAxes() = PointForAxes(c1 * c2, 0.5 * (c1 * c1 + c2 * c2))

    override fun times(times: Double): CoordinatePoint {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}