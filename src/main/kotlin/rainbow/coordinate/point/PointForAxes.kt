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
                invoke(DoubleArray(initValues.size) { initValues[it].toDouble() })

        operator fun invoke(initValues: List<Number>) =
                invoke(DoubleArray(initValues.size) { initValues[it].toDouble() })

        val ZERO = PointForAxes(0)
    }

    //生成size维的值均为value的点
    constructor(value: Double, size: Int) : this(*DoubleArray(size) { value })

    val values: DoubleArray = initValues

    val size: Int
        get() = values.size

    operator fun get(index: Int) = values.getOrElse(index, { 0.0 })//维度不够时补0
    fun getValue(index: Int) = get(index)

    fun plusAtAndNew(index: Int, plus: Number): PointForAxes {
        val newValues = DoubleArray(Math.max(index + 1, size)) { get(it) }
        newValues[index] += plus.toDouble()
        return PointForAxes(newValues)
    }

    fun setAtAndNew(index: Int, value: Number): PointForAxes {
        val newValues = DoubleArray(Math.max(index + 1, size)) { get(it) }
        newValues[index] = value.toDouble()
        return PointForAxes(newValues)
    }

    fun timesAtAndNew(index: Int, times: Number): PointForAxes {
        val newValues = DoubleArray(Math.max(index + 1, size)) { get(it) }
        newValues[index] *= times.toDouble()
        return PointForAxes(newValues)
    }

    override operator fun times(times: Number) = PointForAxes(DoubleArray(size) { get(it) * times.toDouble() })

    override fun toPointForAxes() = this


    override fun toString(): String {
        return "PointForAxes(${Arrays.toString(values)})"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as PointForAxes

        //只要两个点不为0的值均相等即可
        (0..Math.max(this.size, other.size) - 1).forEach {
            if (this[it] != other[it]) return false
        }

        return true
    }

    override fun hashCode(): Int {
        return Arrays.hashCode(values)
    }
}