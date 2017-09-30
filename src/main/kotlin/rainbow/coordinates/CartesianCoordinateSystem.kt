package rainbow.coordinates

import rainbow.input.KeyMap
import rainbow.point.CoordinatePoint
import rainbow.point.Point2D
import rainbow.point.PointAxes
import rainbow.utils.*
import java.lang.Math.*
import java.awt.event.KeyEvent.*

/**
 * 任意维度的轴坐标系
 *
 * 但由于其任意纬度的特性，故继承[CoordinateSystem2D]
 *
 * @author Rainbow Yang
 * @see CoordinateSystem2D
 */
abstract class CartesianCoordinateSystem : CoordinateSystem2D() {

    var axes = mutableListOf<Axis>()
    val size get() = axes.size

    override var coordinateTransformComponent = CoordinateTransformComponent()
    override var painter: CoordinateSystem.Painter = Painter()

    abstract fun setDefaultAxes(size: Int)

    fun addAxes(vararg axes: Axis) = axes.forEach { this.axes.add(it) }
    fun addAxes(axes: List<out Axis>) = axes.forEach { this.axes.add(it) }

    /**
     * [CartesianCoordinateSystem]的核心组成部分，每根轴都应该使用[Axis]的子类来表示
     *
     * 须提供[angle] (弧度)和[length]
     */
    abstract class Axis {
        abstract var angle: Double
        var angleAsDegree: Double
            get() = angle.toDegrees()
            set(value) {
                angle = value.toRadians()
            }

        open var length: Double = 40.0

        operator fun component1() = angle
        operator fun component2() = length

        override fun toString(): String {
            return "Axis(angle=$angle, length=$length)"
        }
    }

    inner class CoordinateTransformComponent : CoordinateSystem.CoordinateTransformComponent() {

        override fun toScreenPoint(cp: CoordinatePoint): Point2D {
            val form = cp.asAxes
            var px = 0.0
            var py = 0.0
            until(axes.size).forEach {
                val (angle, length) = axes[it]
                val value = form[it]
                px += cos(angle) * length * value
                py += sin(angle) * length * value
            }
            return Point2D(px, py).rotateAndScaleAndMove()
        }

        override fun toCoordinatePoint(p: Point2D): CoordinatePoint {
            val pd = p.inverseRotateAndScaleAndMove()

            val (angle0, length0) = axes[0]
            val (angle1, length1) = axes[1]

            val x = pd.spin(PI / 2 - angle1).x
            val y = pd.spin(0 - angle0).y

            val xAngle = angle0 + PI / 2 - angle1
            val yAngle = angle1 - angle0

            val px = x / cos(xAngle) / length0
            val py = y / sin(yAngle) / length1

            return PointAxes(px, py)
        }
    }


    inner class Painter : CoordinateSystem.Painter() {

        var paintRange = 0..30
        val sizeRange get() = until(size)

        override fun paintOrigin(cg: CoordinateGraphics) = cg.paintString("O", PointAxes.ZERO)

        override fun paintGrid(cg: CoordinateGraphics) =
                //维度遍历
                sizeRange.forEach {
                    //向别的维度遍历
                    paintRange.forEach { value ->
                        val p0 = PointAxes.ZERO.plusAtAndNew(it, value)
                        val p1 = p0.plusAtAndNew(if (it == axes.size - 1) 0 else it + 1, 1.0)
                        val p2 = p0.plusAtAndNew(if (it == 0) axes.size - 1 else it - 1, 1.0)

                        cg.paintRayLine(p0, p1)
                        cg.paintRayLine(p0, p2)
                    }
                }

        override fun paintAxes(cg: CoordinateGraphics) =
                sizeRange.forEach { cg.paintStraightLine(PointAxes.ZERO, PointAxes.ZERO.plusAtAndNew(it, 10)) }


        override fun paintNumber(cg: CoordinateGraphics) {
            sizeRange.forEach { size ->
                paintRange.filter { it != 0 }.forEach {
                    cg.paintString(it, PointAxes.ZERO.plusAtAndNew(size, it))
                }
            }
        }
    }
}

class CartesianCoordinateSystemClassic(size: Int = 3) : CartesianCoordinateSystem() {

    init {
        setDefaultAxes(size)
    }

    override fun setDefaultAxes(size: Int) {
        when (size) {
            2 -> addAxesAsDegree(0, 90)
            3 -> addAxesAsDegree(225, 0, 90)
        }
    }

    fun addAxesAsDegree(vararg angles: Number) = angles.forEach { axes.add(ClassicAxis(it.toRadians())) }

    class ClassicAxis(override var angle: Double = 0.0, override var length: Double = 40.0) : Axis() {
        constructor(angle: Number, length: Number = 40.0) : this(angle.toDouble(), length.toDouble())
    }
}

class CartesianCoordinateSystemBall(size: Int = 3) : CartesianCoordinateSystem() {

    var sight: Pair<Number, Number> = 0 to 0
        set(value) {
            field = value
            resetAngleAndLength()
        }

    var xSight: Double
        get() = sight.first.toDouble()
        set(xSight) {
            sight = xSight to ySight
        }

    var ySight: Double
        get() = sight.second.toDouble()
        set(ySight) {
            sight = xSight to ySight
        }

    var sightAsDegree: Pair<Number, Number>
        get() = sight.first.toDegrees() to sight.second.toDegrees()
        set(value) {
            sight = value.first.toRadians() to value.second.toRadians()
        }

    var sightRotateSpeed = 1.01

    init {
        setDefaultAxes(size)
    }

    override fun setDefaultAxes(size: Int) {
        when (size) {
            2 -> addAxesAsDegree(90 to 0, 0 to 90)
            3 -> addAxesAsDegree(0 to 0, 90 to 0, 0 to 90)
        }
    }

    fun addAxesAsDegree(vararg locations: Pair<Number, Number>) =
            locations.forEach { axes.add(BallAxis(it.first.toRadians() to it.second.toRadians())) }

    fun resetAngleAndLength() = axes.forEach { (it as BallAxis).resetAngleAndLength(sight) }

    override fun addKeyHandlesTo(keyMap: KeyMap) {
        super.addKeyHandlesTo(keyMap)

        with(keyMap) {
            VK_W { ySight += sightRotateSpeed * it }
            VK_S { ySight -= sightRotateSpeed * it }
            VK_A { xSight -= sightRotateSpeed * it }
            VK_D { xSight += sightRotateSpeed * it }
        }
    }

    class BallAxis(var xAngle: Double, var yAngle: Double) : Axis() {
        constructor(xAngle: Number, yAngle: Number) : this(xAngle.toDouble(), yAngle.toDouble())
        constructor(location: Pair<Number, Number>) : this(location.first, location.second)

        override var angle: Double = 0.0
        override var length: Double
            get() = originalLength * lengthTimes
            set(value) {}


        var originalLength: Double = 40.0

        var lengthTimes: Double = 1.0
            private set

        var location: Pair<Number, Number>
            get() = xAngle to yAngle
            set(value) {
                xAngle = value.first.toDouble()
                yAngle = value.second.toDouble()
            }

        var locationAsDegree: Pair<Number, Number>
            get() = xAngle.toRadians() to yAngle.toRadians()
            set(value) {
                xAngle = value.first.toRadians()
                yAngle = value.second.toRadians()
            }

        init {
            resetAngleAndLength(0.0, 0.0)
        }

        fun resetAngleAndLength(sight: Pair<Number, Number>) = resetAngleAndLength(sight.first, sight.second)

        fun resetAngleAndLength(xSight: Number, ySight: Number) {
            val thisX = this.xAngle
            val thisY = this.yAngle
            val lookX = xSight.toDouble()
            val lookY = ySight.toDouble()

            //Axis所在的圆的半径
            val R = cos(thisY)

            //Axis所在的圆到赤道面的高度
            val baseY = sin(thisY)

            val x = R * sin(thisX - lookX)
            val y = baseY * cos(lookY) - R * sin(lookY) * cos(thisX - lookX)

            this.angle = atan2(y, x)
            this.lengthTimes = lengthOf(x, y)
        }

        override fun toString(): String {
            return "BallAxis(xAngle=$xAngle, yAngle=$yAngle, angle=$angle, originalLength=$originalLength, lengthTimes=$lengthTimes)"
        }
    }
}