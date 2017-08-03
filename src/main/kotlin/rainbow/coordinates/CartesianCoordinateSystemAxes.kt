package rainbow.coordinates

import rainbow.utils.lengthOf

/**
 * @author Rainbow Yang
 */
/**
 * [angle]使用弧度
 */
abstract class CartesianCoordinateSystemAxis(var angle: Double = 0.0, open var length: Double = 40.0) {
    operator fun component1() = angle
    operator fun component2() = length
}

class ClassicAxis(angle: Double = 0.0, length: Double = 40.0) : CartesianCoordinateSystemAxis(angle, length)
class BallAxis(var xAngle: Double, var yAngle: Double) : CartesianCoordinateSystemAxis() {
    constructor(xAngle: Number, yAngle: Number) : this(xAngle.toDouble(), yAngle.toDouble())

    var originalLength: Double = 40.0
    var lengthTimes: Double = 1.0
        private set

    override var length: Double
        get() = originalLength * lengthTimes
        set(value) {}

    var location: Pair<Number, Number>
        get() = xAngle to yAngle
        set(value) {
            xAngle = value.first.toDouble()
            yAngle = value.second.toDouble()
        }

    init {
        resetAngleAndLength(0.0, 0.0)
    }

    fun resetAngleAndLength(xAngle: Double, yAngle: Double) {
        val thisX = Math.toRadians(this.xAngle)
        val thisY = Math.toRadians(this.yAngle)
        val lookX = Math.toRadians(xAngle)
        val lookY = Math.toRadians(yAngle)

        //Axis所在的圆的半径
        val R = Math.cos(thisY)

        //Axis所在的圆到赤道面的高度
        val baseY = Math.sin(thisY)

        val x = R * Math.sin(thisX - lookX)
        val y = baseY * Math.cos(lookY) - R * Math.sin(lookY) * Math.cos(thisX - lookX)

        this.angle = Math.atan2(y, x)
        this.lengthTimes = lengthOf(x, y)
    }

}