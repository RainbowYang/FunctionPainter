package rainbow.point

import rainbow.utils.component1
import rainbow.utils.component2
import java.lang.Math.sqrt

/**
 * 二维抛物线坐标点
 *
 * @author Rainbow Yang
 */
class Point2DParabolic(val σ: Double, val τ: Double) : Point2D {

    operator fun component1() = σ
    operator fun component2() = τ

    private val c1 get() = σ
    private val c2 get() = τ

    companion object {
        operator fun invoke(form: PointForAxes): Point2DParabolic {
            val (x, y) = form
            return Point2DParabolic(sqrt(-y + sqrt(x * x + y * y)), sqrt(y + sqrt(x * x + y * y)))
        }
    }

    override fun toPointForAxes() = PointForAxes(c1 * c2, 0.5 * (c1 * c1 + c2 * c2))

}