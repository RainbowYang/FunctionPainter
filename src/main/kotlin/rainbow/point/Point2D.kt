package rainbow.point

import rainbow.utils.asPoint2D
import rainbow.utils.asPoint2DPolar
import java.awt.event.MouseEvent

/**
 * 二维点
 * @author Rainbow Yang
 */
class Point2D(val x: Double, val y: Double) : CoordinatePoint {
    constructor(x: Number, y: Number) : this(x.toDouble(), y.toDouble())
    constructor(event: MouseEvent) : this(event.x, event.y)
    constructor(point: PointAxes) : this(point[0], point[1])

    val angle get() = Math.atan2(y, x)

    override val asAxes = PointAxes(x, y)

    fun spin(angle: Double) = asPoint2DPolar.spin(angle).asPoint2D

    override fun toString(): String {
        return "Point2D(x=$x, y=$y)"
    }

}