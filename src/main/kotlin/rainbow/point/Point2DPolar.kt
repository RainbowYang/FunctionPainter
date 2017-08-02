package rainbow.point

import rainbow.utils.PI2
import rainbow.utils.asPoint2D
import java.lang.Math.cos
import java.lang.Math.sin

/**
 * 二维极坐标点
 * @author Rainbow Yang
 */

class Point2DPolar(val r: Double, val angle: Double) : CoordinatePoint {
    constructor(r: Number, angle: Number) : this(r.toDouble(), angle.toDouble())

    operator fun component1() = r
    operator fun component2() = angle

    companion object {
        operator fun invoke(form: CoordinatePoint): Point2DPolar {
            val pd = form.asPoint2D
            return Point2DPolar(pd.length, pd.angle)
        }
    }

    override val asAxes get() = PointAxes(r * cos(angle), r * sin(angle))

    fun spin(angle: Number) = Point2DPolar(r, this.angle + angle.toDouble())

    override fun toString(): String {
        return "PointPolar2D(r=$r, angle=$angle)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        if (other !is Point2DPolar) return false

        return r == other.r && (angle % PI2) == (other.angle % PI2)
    }

    override fun hashCode(): Int {
        var result = r.hashCode()
        result = 31 * result + angle.hashCode()
        return result
    }

}
