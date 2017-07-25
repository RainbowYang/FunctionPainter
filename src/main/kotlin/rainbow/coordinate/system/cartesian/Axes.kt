package rainbow.coordinate.system.cartesian

import java.lang.Math.*
import kotlin.coroutines.experimental.EmptyCoroutineContext.plus

/**
 * 每个[Axis]都将会被视为一个轴，会放在一个球(经纬度坐标)的模型上
 * @author Rainbow Yang
 */
class Axes {
    var axes = mutableListOf<Axis>()

    val size: Int
        get() = axes.size

    operator fun get(index: Int) = axes[index]

    fun addAxis(vararg axes: Axis) = this.axes.addAll(axes)
}

class Axis(xAngle: Double = 0.0, yAngle: Double = 0.0, var originalLength: Double = 40.0) {

    companion object {
        operator fun invoke(xAngle: Number = 0.0, yAngle: Number = 0.0, originalLength: Double = 40.0) =
                Axis(xAngle.toDouble(), yAngle.toDouble(), originalLength)

        fun front() = Axis()
        fun back() = Axis(180, 0)
        fun up() = Axis(0, 90)
        fun down() = Axis(0, -90)
        fun right() = Axis(90, 0)
        fun left() = Axis(-90, 0)
    }

    operator fun component2() = length
    var length: Double
        get() = originalLength * visualTimes
        set(value) {
            originalLength = length / visualTimes
        }

    operator fun component1() = angle
    var angle: Double = 0.0
        private set
    var visualTimes: Double = 0.0
        private set

    var xAngle: Double = xAngle
        private set
    var yAngle: Double = yAngle
        private set

    init {
        resetAngleAndLength()
    }

    var movable = true

    fun move(x: Number, y: Number) {
        if (movable) {
            xAngle += x.toDouble()
            yAngle += y.toDouble()

            resetAngleAndLength()
        }
    }

    private fun resetAngleAndLength() {
        val x = sin(toRadians(xAngle)) * cos(toRadians(yAngle))
        val y = sin(toRadians(yAngle))

        angle = atan2(y, x)
        visualTimes = sqrt(x * x + y * y)
    }

    override fun toString(): String {
        return "Axis(originalLength=$originalLength, angle=$angle, visualTimes=$visualTimes, xAngle=$xAngle, yAngle=$yAngle, movable=$movable)"
    }

}