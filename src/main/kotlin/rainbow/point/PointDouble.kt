package rainbow.point

import rainbow.utils.length
import rainbow.utils.similarEquals
import rainbow.utils.toPointDouble
import rainbow.utils.toPointPolar2D
import java.awt.event.MouseEvent
import java.lang.Math.atan2

/**
 * 用于表示屏幕上的位置
 *
 * @author Rainbow Yang
 */
class PointDouble(var x: Double, var y: Double) : CoordinatePoint {
    constructor(x: Number, y: Number) : this(x.toDouble(), y.toDouble())
    constructor(event: MouseEvent) : this(event.x, event.y)
    constructor(point: PointForAxes) : this(point[0], point[1])

    val available get() = !x.isNaN() && !y.isNaN()
    val length get() = length(x, y)
    val angle get() = atan2(y, x)

    override operator fun times(times: Double) = PointDouble(x * times, y * times)

    operator fun plus(other: PointDouble) = PointDouble(x + other.x, y + other.y)
    operator fun minus(other: PointDouble) = PointDouble(x - other.x, y - other.y)

    fun spin(angle: Double) = toPointPolar2D().spin(angle).toPointDouble()
//    = PointDouble(x * cos(angle) - y * sin(angle), x * sin(angle) + y * cos(angle))

    override fun toPointForAxes() = PointForAxes(x, y)

    override fun toString() = "PointDouble(x=$x, y=$y)"

    override fun equals(other: Any?): Boolean {
        if (other !is PointDouble) return false

        return x similarEquals other.x && y similarEquals other.y
    }
}