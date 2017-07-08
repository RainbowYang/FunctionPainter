package rainbow.coordinate.point

/**
 * 二维极坐标点
 * @author Rainbow Yang
 */

class PointPolar2D(val r: Double, val angle: Double) : CoordinatePoint {
    override fun times(times: Number) = PointPolar2D(r * times.toDouble(), angle)
    override fun toPointForAxes() = PointForAxes(r * Math.cos(angle), r * Math.sin(angle))

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as PointPolar2D

        if (r != other.r) return false
        if (angle != other.angle) return false

        return true
    }

    override fun hashCode(): Int {
        var result = r.hashCode()
        result = 31 * result + angle.hashCode()
        return result
    }


}
