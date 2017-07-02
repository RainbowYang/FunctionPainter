package rainbow.inner.coordinate.point

/**
 * 用于表示屏幕上的位置
 *
 * @author Rainbow Yang
 */
class PointDouble(val x: Double, val y: Double) {

    constructor(x: Int, y: Int) : this(x.toDouble(), y.toDouble())

    fun spin(angle: Double): PointDouble {
        return PointDouble(x * Math.cos(angle) - y * Math.sin(angle), x * Math.sin(angle) + y * Math.cos(angle))
    }

    override fun toString(): String {
        return "PointDouble(x=$x, y=$y)"
    }

}