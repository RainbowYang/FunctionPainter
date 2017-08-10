package rainbow.point

/**
 * 表示坐标系上的一个点
 *
 * 所有子类的所有操作均不应修改其本身，而是返回一个新的类
 *
 * 所有点之间都应能够进行互相转换，可以用[PointAxes]作为中介
 *
 * @author Rainbow Yang
 */
interface CoordinatePoint {

    /**
     * 转换为[PointAxes]
     */
    val asAxes: PointAxes

    /**
     * 该点中是否没有Double.NaN之类无效的值
     */
    val available: Boolean get() = asAxes.available

    /**
     * 默认通过[PointAxes]进行计算，值为其模长
     */
    val length: Double get() = asAxes.length

    operator fun plus(other: CoordinatePoint): CoordinatePoint = asAxes.plus(other.asAxes)
    operator fun times(times: Double): CoordinatePoint = asAxes * times

    operator fun minus(other: CoordinatePoint) = plus(-other)
    operator fun unaryMinus() = times(-1)

    operator fun times(times: Number): CoordinatePoint = times(times.toDouble())
    operator fun div(divTimes: Number): CoordinatePoint = times(1 / divTimes.toDouble())

}