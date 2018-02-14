package rainbow.point

import rainbow.utils.math.PI2
import rainbow.utils.asPoint2D
import java.lang.Math.cos
import java.lang.Math.sin

/**
 * 二维极坐标点
 * @author Rainbow Yang
 */

class Point2DPolar(val radius: Double, val angle: Double) : CoordinatePoint {
    constructor(radius: Number, angle: Number) : this(radius.toDouble(), angle.toDouble())

    operator fun component1() = radius
    operator fun component2() = angle

    companion object {
        operator fun invoke(form: CoordinatePoint): Point2DPolar {
            val pd = form.asPoint2D
            return Point2DPolar(pd.length, pd.angle)
        }
    }

    override val asAxes by lazy { PointAxes(radius * cos(angle), radius * sin(angle)) }

    /**
     * 逆时针旋转[angle]【弧度】
     */
    fun spin(angle: Number) = Point2DPolar(radius, this.angle + angle.toDouble())

    override fun toString() = "PointPolar2D(radius=$radius, angle=$angle)"


    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        if (other !is Point2DPolar) return false

        return radius == other.radius && (angle % PI2) == (other.angle % PI2)
    }

    override fun hashCode(): Int {
        var result = radius.hashCode()
        result = 31 * result + angle.hashCode()
        return result
    }

}
