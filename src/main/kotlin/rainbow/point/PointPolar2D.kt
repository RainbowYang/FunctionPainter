package rainbow.point

import rainbow.utils.PI2
import rainbow.utils.toPointDouble

/**
 * 二维极坐标点
 * @author Rainbow Yang
 */

class PointPolar2D(val r: Double, val angle: Double) : CoordinatePoint {
    constructor(r: Number, angle: Number) : this(r.toDouble(), angle.toDouble())

    companion object {
        operator fun invoke(form: CoordinatePoint): PointPolar2D {
            val pd = form.toPointForAxes().toPointDouble()
            return PointPolar2D(pd.length, pd.angle)
        }
    }

    override fun times(times: Double) = PointPolar2D(r * times, angle)

    fun spin(angle: Number) = PointPolar2D(r, this.angle + angle.toDouble())


    override fun toPointForAxes() = PointForAxes(r * Math.cos(angle), r * Math.sin(angle))

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        if (other !is PointPolar2D) return false

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
