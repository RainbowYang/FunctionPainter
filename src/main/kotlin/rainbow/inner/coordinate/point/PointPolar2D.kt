package rainbow.inner.coordinate.point

/**
 * 二维极坐标点
 * @author Rainbow Yang
 */

class PointPolar2D(val r: Double, val angle: Double) : MyPoint {
    override fun toPointForAxes() = PointForAxes(r * Math.cos(angle), r * Math.sin(angle))
    override fun times(times: Double) = PointPolar2D(r * times, angle)
}
