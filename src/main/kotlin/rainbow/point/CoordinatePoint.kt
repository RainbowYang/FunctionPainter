package rainbow.point

/**
 * 用于表示坐标系上的一个点
 *
 * 所有子类的所有操作均不应修改其本身
 *
 * 所有点之间都应能够进行互相转换
 * 可以用[PointForAxes]作为中介
 *
 * @author Rainbow Yang
 */
interface CoordinatePoint {
    fun toPointForAxes(): PointForAxes

    operator fun times(times: Double): CoordinatePoint = toPointForAxes().times(times)

    operator fun times(times: Number): CoordinatePoint = times(times.toDouble())
    operator fun div(divTimes: Number): CoordinatePoint = times(1 / divTimes.toDouble())

    operator fun unaryMinus() = times(-1)
}