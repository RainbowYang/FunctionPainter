package rainbow.coordinates.cartesian

import rainbow.point.Point2D

/**
 * 适用于[CartesianCoordinateSystem]
 *
 * 表示为一个轴的视觉效果
 *
 * 由一个[Point2D]表示其x轴偏离和y轴偏离
 */
abstract class Axis {

    abstract val x: Double
    abstract val y: Double

    operator fun component1() = x
    operator fun component2() = y

    override fun toString(): String {
        return "Axis(x=$x, y=$y)"
    }

}

class ClassicAxis(override var x: Double, override var y: Double) : Axis()
class PolarAxis(var angle: Double, var length: Double) : Axis() {
    override val x get() = Math.cos(angle) * length
    override val y get() = Math.sin(angle) * length
}