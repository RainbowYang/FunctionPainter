package rainbow.point

import rainbow.utils.checkValues
import rainbow.utils.lengthOf
import rainbow.utils.rangeTo
import java.lang.Math.max
import java.util.*

/**
 * 任意纬度的坐标轴点
 *
 * @author Rainbow Yang
 */
class PointAxes constructor(vararg initValues: Double) : CoordinatePoint {

    //生成size维的值均为value的点
    constructor(value: Double, size: Int) : this(*DoubleArray(size) { value })

    companion object {
        operator fun invoke(initValues: DoubleArray) = PointAxes(*initValues)
        operator fun invoke(vararg initValues: Number) = this(initValues.asList())
        operator fun invoke(initValues: List<Number>) =
                invoke(DoubleArray(initValues.size) { initValues[it].toDouble() })

        val ZERO = PointAxes()
    }

    val values = initValues
    val size get() = values.size

    operator fun get(index: Int) = values.getOrElse(index, { 0.0 })//维度不够时补0

    override val asAxes = this

    override val available = checkValues(values.asList())
    override val length = lengthOf(values.asList())

    override fun plus(other: CoordinatePoint): CoordinatePoint {
        val paOther = other.asAxes
        return PointAxes(DoubleArray(max(size, paOther.size)) { this[it] + paOther[it] })
    }

    override operator fun times(times: Double) = PointAxes(DoubleArray(size) { get(it) * times })


    fun plusAtAndNew(index: Int, plus: Number): PointAxes =
            PointAxes(createNewArrayWithOldData(index).apply { this[index] += plus.toDouble() })

    fun setAtAndNew(index: Int, value: Number): PointAxes =
            PointAxes(createNewArrayWithOldData(index).apply { this[index] = value.toDouble() })

    fun timesAtAndNew(index: Int, times: Number): PointAxes =
            PointAxes(createNewArrayWithOldData(index).apply { this[index] *= times.toDouble() })

    private fun createNewArrayWithOldData(index: Int) = DoubleArray(max(index + 1, size)) { get(it) }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        if (other !is PointAxes) return false

        //只要两个点不为0的值均相等即可 这意味着(1,1,0) equals (1,1,0,0) = true
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