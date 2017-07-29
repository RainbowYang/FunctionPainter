package rainbow.point

import rainbow.utils.PI2

/**
 * 二维极坐标点
 * @author Rainbow Yang
 */

class PointPolar2D(val r: Double, val angle: Double) : CoordinatePoint {
    constructor(r: Number, angle: Number) : this(r.toDouble(), angle.toDouble())

    override fun times(times: Number) = PointPolar2D(r * times.toDouble(), angle)

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


}
