package rainbow.point

import rainbow.utils.rangeTo
import java.util.*
import java.lang.Math.max

/**
 * 任意纬度的坐标轴点
 *
 * @author Rainbow Yang
 */
class PointForAxes constructor(vararg initValues: Double) : CoordinatePoint {

    //生成size维的值均为value的点
    constructor(value: Double, size: Int) : this(*DoubleArray(size) { value })

    companion object {

        operator fun invoke(initValues: DoubleArray) = PointForAxes(*initValues)

        operator fun invoke(vararg initValues: Number) = this(initValues.asList())

        operator fun invoke(initValues: List<Number>) =
                invoke(DoubleArray(initValues.size) { initValues[it].toDouble() })

        val ZERO = PointForAxes(0)
    }

    val values = initValues
    val size get() = values.size

    operator fun get(index: Int) = values.getOrElse(index, { 0.0 })//维度不够时补0

    fun plusAtAndNew(index: Int, plus: Number): PointForAxes =
            PointForAxes(createNewArrayWithOldData(index).apply { this[index] += plus.toDouble() })

    fun setAtAndNew(index: Int, value: Number): PointForAxes =
            PointForAxes(createNewArrayWithOldData(index).apply { this[index] = value.toDouble() })

    fun timesAtAndNew(index: Int, times: Number): PointForAxes =
            PointForAxes(createNewArrayWithOldData(index).apply { this[index] *= times.toDouble() })

    private fun createNewArrayWithOldData(index: Int) = DoubleArray(max(index + 1, size)) { get(it) }

    override operator fun times(times: Double) = PointForAxes(DoubleArray(size) { get(it) * times.toDouble() })

    override fun toPointForAxes() = this

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        if (other !is PointForAxes) return false

        //只要两个点不为0的值均相等即可
        //这意味着(1,1,0) equals (1,1,0,0) = true
        rangeTo(max(this.size, other.size)).forEach {
            if (this[it] != other[it]) return false
        }

        return true
    }

    override fun hashCode(): Int {
        return Arrays.hashCode(values)
    }

    override fun toString(): String {
        return "PointForAxes(${Arrays.toString(values)})"
    }
}