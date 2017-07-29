package rainbow.point

import rainbow.utils.similarEquals
import java.awt.event.MouseEvent

/**
 * 用于表示屏幕上的位置
 *
 * @author Rainbow Yang
 */
class PointDouble(var x: Double, var y: Double) {
    constructor(x: Number, y: Number) : this(x.toDouble(), y.toDouble())
    constructor(event: MouseEvent) : this(event.x, event.y)

    val available: Boolean
        get() = !x.isNaN() && !y.isNaN()

    operator fun plus(other: PointDouble): PointDouble = PointDouble(x + other.x, y + other.y)

    operator fun times(times: Number): PointDouble = PointDouble(x * times.toDouble(), y * times.toDouble())

    fun spin(angle: Double): PointDouble
            = PointDouble(x * Math.cos(angle) - y * Math.sin(angle), x * Math.sin(angle) + y * Math.cos(angle))

    override fun toString(): String {
        return "PointDouble(x=$x, y=$y)"
    }

    override fun equals(other: Any?): Boolean {
        if (other !is PointDouble) return false

        return x similarEquals other.x && y similarEquals other.y
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        return result
    }


}