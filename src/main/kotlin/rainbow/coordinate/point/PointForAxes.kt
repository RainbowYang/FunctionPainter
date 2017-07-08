package rainbow.coordinate.point

import java.util.*

/**
 * 任意纬度的坐标轴点
 *
 * @author Rainbow Yang
 */
class PointForAxes constructor(vararg initValues: Double) : CoordinatePoint {
    companion object {
        operator fun invoke(initValues: DoubleArray) = PointForAxes(*initValues)
        operator fun invoke(vararg initValues: Number) =
                invoke(kotlin.DoubleArray(initValues.size) { initValues[it].toDouble() })

        val ZERO = PointForAxes(0)
    }

    //生成size维的值均为value的点
    constructor(value: Double, size: Int) : this(*DoubleArray(size) { _ -> value })

    init {
    }

    val values: DoubleArray = initValues

    val size: Int
        get() = values.size

    fun getValue(index: Int) = values.getOrNull(index) ?: 0.0 //用于低维向高维转换，维度不够时补0

    fun changeValueAsNew(index: Int, value: Double): PointForAxes {
        val newValues = values.clone()
        newValues[index] = value
        return PointForAxes(newValues)
    }

    override operator fun times(times: Double) = PointForAxes(DoubleArray(size) { it -> times * getValue(it) })

    override operator fun unaryMinus() = times(-1.0)

    override fun toPointForAxes() = this

    override fun toString(): String {
        return "PointForAxes(values=${Arrays.toString(values)})"
    }
}