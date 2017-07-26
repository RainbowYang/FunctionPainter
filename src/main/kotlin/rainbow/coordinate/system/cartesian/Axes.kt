package rainbow.coordinate.system.cartesian

import java.lang.Math.*

/**
 * 每个[Axis]都将会被视为一个轴，会放在一个球(经纬度坐标)的模型上
 * @author Rainbow Yang
 */
class Axes {
    var sight = Axis(0, 0)

    fun moveSight(x: Number, y: Number) {
        sight.xAngle += x.toDouble()
        sight.yAngle += y.toDouble()

        axes.forEach { it.recalculateAngleAndTimes(sight) }
    }


    var axes = mutableListOf<Axis>()

    val size: Int
        get() = axes.size

    operator fun get(index: Int) = axes[index]

    fun addAxis(vararg axes: Axis) {
        this.axes.addAll(axes)
        axes.forEach { it.recalculateAngleAndTimes(sight) }
    }
}

class Axis(var xAngle: Double = 0.0, var yAngle: Double = 0.0, var originalLength: Double = 40.0) {

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

    operator fun component1() = angle
    operator fun component2() = length

    var length: Double
        get() = originalLength * visualTimes
        set(value) {
            originalLength = length / visualTimes
        }
    var angle: Double = 0.0
        private set
    var visualTimes: Double = 0.0
        private set

    fun recalculateAngleAndTimes(sight: Axis) {
        val thisX = toRadians(xAngle)
        val thisY = toRadians(yAngle)
        val sightX = toRadians(sight.xAngle)
        val sightY = toRadians(sight.yAngle)

        val x = sin(thisX - sightX) * cos(thisY)
        val y = sin(thisY) * cos(sightY) - cos(thisY) * sin(sightY)

        angle = atan2(y, x)
        visualTimes = sqrt(x * x + y * y)
    }

    override fun toString(): String {
        return "Axis(originalLength=$originalLength, angle=$angle, visualTimes=$visualTimes, xAngle=$xAngle, yAngle=$yAngle)"
    }

//    private fun resetAngleAndLength() {
//        MySystem.coordinateSystem.axes.setLengthTimes(2, Math.cos(Math.toRadians(yAngle)))
//
//        var x = -Math.sin(Math.toRadians(xAngle))
//        var y = -Math.cos(Math.toRadians(xAngle)) * Math.sin(Math.toRadians(yAngle))
//
//        MySystem.coordinateSystem.axes.setAngle(0, Math.atan2(y, x))
//        MySystem.coordinateSystem.axes.setLengthTimes(0, Math.sqrt(x * x + y * y))
//
//        x = -Math.sin(Math.toRadians(xAngle - 90))
//        y = -Math.cos(Math.toRadians(xAngle - 90)) * Math.sin(Math.toRadians(yAngle))
//
//        MySystem.coordinateSystem.axes.setAngle(1, Math.atan2(y, x))
//        MySystem.coordinateSystem.axes.setLengthTimes(1, Math.sqrt(x * x + y * y))
//
//    }

}