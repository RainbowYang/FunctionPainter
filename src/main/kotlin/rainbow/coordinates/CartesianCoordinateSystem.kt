package rainbow.coordinates

import com.google.gson.annotations.Expose
import rainbow.point.CoordinatePoint
import rainbow.point.Point2D
import rainbow.point.PointAxes
import rainbow.utils.CoordinateGraphics
import rainbow.utils.lengthOf
import rainbow.utils.println
import rainbow.utils.rangeTo
import java.awt.event.MouseEvent

/**
 * 任意维度的轴坐标系
 *
 * 但由于其任意纬度的特性，故继承[CoordinateSystem2D]
 *
 * @author Rainbow Yang
 * @see CoordinateSystem2D
 */
abstract class CartesianCoordinateSystem : CoordinateSystem2D() {

    @Expose override var type = super.type

    override var origin = super.origin
    @Expose override var zoomRate = super.zoomRate
    @Expose override var rotatedAngle = super.rotatedAngle

    @Expose var axes = mutableListOf<Axis>()
    val size get() = axes.size

    @Expose override var inputComponent: rainbow.component.InputListenComponent = super.inputComponent
    @Expose override var paintComponent: CoordinateSystem.PaintComponent = PaintComponent(this)

    override var coordinateTransformComponent: rainbow.component.CoordinateTransformComponent
            = CoordinateTransformComponent(this)

    abstract fun setDefaultAxes(size: Int)

    fun addAxes(vararg axes: Axis) = axes.forEach { this.axes.add(it) }
    fun addAxes(axes: List<out Axis>) = axes.forEach { this.axes.add(it) }

    override fun toString(): String {
        return "CartesianCoordinateSystem(type='$type', x=$x, y=$y, zoomRate=$zoomRate, rotatedAngle=$rotatedAngle, axes=$axes, inputComponent=$inputComponent, paintComponent=$paintComponent, coordinateTransformComponent=$coordinateTransformComponent)"
    }

    /**
     * [CartesianCoordinateSystem]的核心組成部分，每根轴都应该使用[Axis]的子类来表示
     *
     * 必须要提供[angle]和[length]
     */
    abstract class Axis {
        abstract var angle: Double
        open var length: Double = 40.0
        operator fun component1() = angle
        operator fun component2() = length

        override fun toString(): String {
            return "Axis(angle=$angle, length=$length)"
        }
    }


    open class InputListenComponent(system: CartesianCoordinateSystem) :
            CoordinateSystem2D.InputListenComponent(system) {

        @Expose var xAngle = 0.0
        @Expose var yAngle = 0.0

        var look: Pair<Number, Number>
            get() = xAngle to yAngle
            set(value) {
                xAngle = value.first.toDouble()
                yAngle = value.second.toDouble()
            }

        override fun mouseDragged(e: MouseEvent) {
            system as CartesianCoordinateSystem

            if (firstEvent.button == MouseEvent.BUTTON2) {
                xAngle -= (e.x - lastEvent.x) / 10.0
                yAngle += (e.y - lastEvent.y) / 10.0

                system.axes.forEach { (it as CartesianCoordinateSystemBall.BallAxis).resetAngleAndLength(xAngle, yAngle) }
            }

            super.mouseDragged(e)
        }
    }

    open class CoordinateTransformComponent(val system: CartesianCoordinateSystem) :
            rainbow.component.CoordinateTransformComponent() {

        // todo 弧度与角度
        override fun toScreenPoint(cp: CoordinatePoint): Point2D = with(system) {
            val form = cp.asAxes
            var px = 0.0
            var py = 0.0
            for (i in 0..axes.size - 1) {
                val (angle, length) = axes[i]
                px += Math.cos(angle) * length * form[i]
                py += Math.sin(angle) * length * form[i]
            }
            return Point2D(px, py).rotateAndScaleAndMove()
        }


        override fun toCoordinatePoint(p: Point2D): CoordinatePoint = with(system) {
            val pd = p.inverseRotateAndScaleAndMove()

            val x = pd.spin(Math.PI / 2 - axes[1].angle).x
            val y = pd.spin(0 - axes[0].angle).y

            val xAngle = axes[0].angle + Math.PI / 2 - axes[1].angle
            val yAngle = axes[1].angle - axes[0].angle

            val px = x / Math.cos(xAngle) / axes[0].length
            val py = y / Math.sin(yAngle) / axes[1].length

            return PointAxes(px, py)

        }
    }


    open class PaintComponent(val system: CartesianCoordinateSystem) : CoordinateSystem.PaintComponent(system) {

        var paintRange = 0..30
        val sizeRange get() = rangeTo(system.size)

        init {
            addPaintPart(ORIGIN) { paintOrigin(it) }
            addPaintPart(GRID) { paintGrid(it) }
            addPaintPart(AXES) { paintAxes(it) }
            addPaintPart(NUMBER) { paintNumber(it) }
        }

        fun paintOrigin(cg: CoordinateGraphics) = cg.paintString("O", PointAxes.ZERO)

        fun paintGrid(cg: CoordinateGraphics) = with(system) {
            //维度遍历
            for (i in sizeRange) {
                //值遍历
                for (value in paintRange) {
                    val p0 = PointAxes.ZERO.plusAtAndNew(i, value)
                    val p1 = p0.plusAtAndNew(if (i == axes.size - 1) 0 else i + 1, 1.0)
                    val p2 = p0.plusAtAndNew(if (i == 0) axes.size - 1 else i - 1, 1.0)

                    cg.paintRayLine(p0, p1)
                    cg.paintRayLine(p0, p2)
                }
            }
        }

        fun paintAxes(cg: CoordinateGraphics) = with(system) {
            sizeRange.forEach {
                cg.paintStraightLine(PointAxes.ZERO, PointAxes.ZERO.plusAtAndNew(it, 10))
            }
        }

        fun paintNumber(cg: CoordinateGraphics) = with(system) {
            sizeRange.forEach { size ->
                paintRange.filter { it != 0 }.forEach {
                    cg.paintString(it, PointAxes.ZERO.plusAtAndNew(size, it))
                }
            }
        }
    }
}

class CartesianCoordinateSystemClassic(size: Int = -1) : CartesianCoordinateSystem() {
    init {
        setDefaultAxes(size)
    }

    override fun setDefaultAxes(size: Int) {
        when (size) {
            2 -> addAxes(0, 90)
            3 -> addAxes(180, 0, 90)
        }
    }

    fun addAxes(vararg angles: Number) = angles.forEach { axes.add(ClassicAxis(it)) }

    class ClassicAxis(@Expose override var angle: Double = 0.0, @Expose override var length: Double = 40.0) : Axis() {
        constructor(angle: Number, length: Number = 40.0) : this(angle.toDouble(), length.toDouble())
    }
}

class CartesianCoordinateSystemBall(size: Int = -1) : CartesianCoordinateSystem() {

    init {
        setDefaultAxes(size)
        inputComponent = InputListenComponent(this)
    }

    override fun setDefaultAxes(size: Int) {
        when (size) {
            2 -> addAxes(90 to 0, 0 to 90)
            3 -> addAxes(0 to 0, 90 to 0, 0 to 90)
        }
    }

    fun addAxes(vararg locations: Pair<Number, Number>) = locations.forEach { axes.add(BallAxis(it)) }

    class BallAxis(@Expose var xAngle: Double, @Expose var yAngle: Double) : Axis() {
        constructor(xAngle: Number, yAngle: Number) : this(xAngle.toDouble(), yAngle.toDouble())
        constructor(location: Pair<Number, Number>) : this(location.first, location.second)

        override var angle: Double = 0.0
        override var length: Double
            get() = originalLength * lengthTimes
            set(value) {}

        @Expose var originalLength: Double = 40.0

        var lengthTimes: Double = 1.0
            private set

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

        override fun toString(): String {
            return "BallAxis(xAngle=$xAngle, yAngle=$yAngle, angle=$angle, originalLength=$originalLength, lengthTimes=$lengthTimes)"
        }


    }
}