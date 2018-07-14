package rainbow.point

import rainbow.utils.asPoint2D
import rainbow.utils.asPoint2DPolar
import java.awt.event.MouseEvent

/**
 * 二维点
 * @author Rainbow Yang
 */
class Point2D(val x: Double, val y: Double) : CoordinatePoint {
    constructor() : this(0, 0)
    constructor(x: Number, y: Number) : this(x.toDouble(), y.toDouble())
    constructor(event: MouseEvent) : this(event.x, event.y)
    constructor(point: PointAxes) : this(point[0], point[1])

    operator fun component1() = x
    operator fun component2() = y

    val angle get() = Math.atan2(y, x)

    override val asAxes by lazy { PointAxes(x, y) }

    /**
     * 逆时针旋转[angle]【弧度】
     */
    fun spin(angle: Double) = asPoint2DPolar.spin(angle).asPoint2D

    override fun toString() = "Point2D(x=$x, y=$y)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Point2D

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x.hashCode()
        result = 31 * result + y.hashCode()
        return result
    }


}