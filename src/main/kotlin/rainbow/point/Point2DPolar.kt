package rainbow.point

import rainbow.utils.PI2
import rainbow.utils.toPointDouble

/**
 * 二维极坐标点
 * @author Rainbow Yang
 */

class Point2DPolar(val r: Double, val angle: Double) : Point2D {
    constructor(r: Number, angle: Number) : this(r.toDouble(), angle.toDouble())

    operator fun component1() = r
    operator fun component2() = angle

    companion object {
        operator fun invoke(form: CoordinatePoint): Point2DPolar {
            val pd = form.toPointForAxes().toPointDouble()
            return Point2DPolar(pd.length, pd.angle)
        }
    }

    override fun times(times: Double) = Point2DPolar(r * times, angle)

    fun spin(angle: Number) = Point2DPolar(r, this.angle + angle.toDouble())


    override fun toPointForAxes() = PointForAxes(r * Math.cos(angle), r * Math.sin(angle))

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

    override fun toString(): String {
        return "PointPolar2D(r=$r, angle=$angle)"
    }

}
