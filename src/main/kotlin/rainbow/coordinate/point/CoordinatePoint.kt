package rainbow.coordinate.point

/**
 * 用于表示坐标系上的一个点
 * 所有子类的所有操作均不应修改其本身
 * @author Rainbow Yang
 */
interface CoordinatePoint {
    fun toPointForAxes(): PointForAxes

    operator fun times(times: Number): CoordinatePoint
    operator fun unaryMinus() = times(-1)
}