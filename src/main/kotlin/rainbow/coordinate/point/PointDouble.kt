package rainbow.coordinate.point

/**
 * 用于表示屏幕上的位置
 *
 * @author Rainbow Yang
 */
class PointDouble(var x: Double, var y: Double) {
    constructor(x: Number, y: Number) : this(x.toDouble(), y.toDouble())

    val available: Boolean
        get() = (x != Double.NaN) && (y != Double.NaN)

    fun plus(other: PointDouble): PointDouble = PointDouble(x + other.x, y + other.y)

    fun times(times: Double): PointDouble = PointDouble(x * times, y * times)

    fun spin(angle: Double): PointDouble
            = PointDouble(x * Math.cos(angle) - y * Math.sin(angle), x * Math.sin(angle) + y * Math.cos(angle))

    override fun toString(): String {
        return "PointDouble(x=$x, y=$y)"
    }

}