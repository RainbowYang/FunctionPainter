package rainbow.coordinate.system.cartesian

/**
 * For CartesianCoordinateSystem
 *
 * 用于存储Axis及其子类
 *
 * @author Rainbow Yang
 */
class Axes(initSize: Int) {
    val axes = MutableList(initSize) { Axis() }
    val size: Int
        get() = axes.size

    operator fun get(index: Int): Axis = axes[index]

    fun setAngle(vararg angles: Number) {
        for ((index, value) in angles.withIndex()) {
            if (index <= size)
                axes[index].angle = value.toDouble()
            else return
        }
    }

    fun setAngleByDegrees(vararg angles: Number) {
        for ((index, value) in angles.withIndex()) {
            if (index <= size)
                axes[index].angle = Math.toRadians(value.toDouble())
            else return
        }
    }

    override fun toString(): String {
        return "$axes"
    }


}

open class Axis(var angle: Double = 0.0, var length: Double = 40.0) {
    constructor() : this(0.0, 40.0)
    constructor(angle: Number = 0.0, length: Number = 40.0) : this(angle.toDouble(), length.toDouble())

    operator fun component1() = angle
    operator fun component2() = length

    override fun toString(): String {
        return "Axis(angle=$angle, length=$length)"
    }
}