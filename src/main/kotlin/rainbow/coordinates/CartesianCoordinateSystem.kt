package rainbow.coordinates

import rainbow.component.CoordinateTransformComponent
import rainbow.component.InputListenComponent
import rainbow.point.CoordinatePoint
import rainbow.point.PointDouble
import rainbow.point.PointForAxes
import rainbow.utils.CoordinateGraphics
import rainbow.utils.rangeTo
import rainbow.utils.length
import java.awt.event.MouseEvent
import java.lang.Math.*

/**
 * 任意维度的轴坐标系
 *
 * 但由于其任意纬度的特性，可能不会有专门的立体效果
 * 故继承[CoordinateSystem2D]
 *
 * @author Rainbow Yang
 * @see CoordinateSystem2D
 */
class CartesianCoordinateSystem(size: Int) : CoordinateSystem2D() {

    override var coordinateTransformComponent: CoordinateTransformComponent
            = CoordinateTransformComponentForCartesianCoordinateSystem(this)

    override var paintComponent: CoordinateSystemPainter
            = PainterForCartesianCoordinateSystem(this)

    override var inputComponent: InputListenComponent
            = InputListenerForCartesianCoordinateSystem(this)

    val axes = mutableListOf<Axis>()
    val size: Int get() = axes.size

    var paintAsBall = true

    init {
        setDefaultAxes(size)
    }

    private fun setDefaultAxes(size: Int) {
        when (size) {
            3 -> {
                if (paintAsBall) {
                    addAxes(0 to 0, 90 to 0, 0 to 90)
                } else {
                    addAxes(225, 0, 90)
                }
            }
            2 -> {
                paintAsBall = false

                addAxes(0, 90)
            }
        }
    }

    fun addAxes(vararg axis: Pair<Number, Number>) = axis.forEach { axes.add(BallAxis(it.first, it.second)) }
    fun addAxes(vararg angles: Number) = angles.forEach { axes.add(ClassicAxis(toRadians(it.toDouble()))) }

    /**
     * [angle]使用弧度
     */
    abstract class Axis(var angle: Double = 0.0, open var length: Double = 40.0) {
        operator fun component1() = angle
        operator fun component2() = length
    }

    class ClassicAxis(angle: Double = 0.0, length: Double = 40.0) : Axis(angle, length)
    class BallAxis(var xAngle: Double, var yAngle: Double) : Axis() {
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

        fun resetAngleAndLength(xAngle: Double, yAngle: Double) {
            val thisX = toRadians(this.xAngle)
            val thisY = toRadians(this.yAngle)
            val lookX = toRadians(xAngle)
            val lookY = toRadians(yAngle)

            //Axis所在的圆的半径
            val R = cos(thisY)

            //Axis所在的圆到赤道面的高度
            val baseY = sin(thisY)

            val x = R * sin(thisX - lookX)
            val y = baseY * cos(lookY) - R * sin(lookY) * cos(thisX - lookX)

            this.angle = atan2(y, x)
            this.lengthTimes = length(x, y)
        }

    }


    open class CoordinateTransformComponentForCartesianCoordinateSystem(val system: CartesianCoordinateSystem) :
            CoordinateTransformComponent() {
        override fun toScreenPoint(cp: CoordinatePoint): PointDouble = with(system) {
            val form = cp.toPointForAxes()
            var px = 0.0
            var py = 0.0
            for (i in 0..axes.size - 1) {
                val (angle, length) = axes[i]
                px += cos(angle) * length * form[i]
                py += sin(angle) * length * form[i]
            }
            return rotateAndScaleAndMove(PointDouble(px, py))
        }


        override fun toCoordinatePoint(p: PointDouble): CoordinatePoint = with(system) {
            val pd = inverseRotateAndScaleAndMove(p)

            val x = pd.spin(Math.PI / 2 - axes[1].angle).x
            val y = pd.spin(0 - axes[0].angle).y

            val xAngle = axes[0].angle + Math.PI / 2 - axes[1].angle
            val yAngle = axes[1].angle - axes[0].angle

            val px = x / Math.cos(xAngle) / axes[0].length
            val py = y / Math.sin(yAngle) / axes[1].length

            return PointForAxes(px, py)

        }
    }

    open class PainterForCartesianCoordinateSystem(val system: CartesianCoordinateSystem) :
            CoordinateSystemPainter(system) {

        var paintRange = 0..30
        val sizeRange
            get() = rangeTo(system.size)

        override fun paintOrigin(cg: CoordinateGraphics) = cg.paintString("O", PointForAxes.ZERO)


        override fun paintGrid(cg: CoordinateGraphics) = with(system) {
            //维度遍历
            for (i in sizeRange) {
                //值遍历
                for (value in paintRange) {
                    val p0 = PointForAxes.ZERO.plusAtAndNew(i, value)
                    val p1 = p0.plusAtAndNew(if (i == axes.size - 1) 0 else i + 1, 1.0)
                    val p2 = p0.plusAtAndNew(if (i == 0) axes.size - 1 else i - 1, 1.0)

                    cg.paintRayLine(p0, p1)
                    cg.paintRayLine(p0, p2)
                }
            }
        }

        override fun paintAxes(cg: CoordinateGraphics) = with(system) {
            sizeRange.forEach {
                cg.paintStraightLine(PointForAxes.ZERO, PointForAxes.ZERO.plusAtAndNew(it, 10))
            }
        }

        override fun paintNumber(cg: CoordinateGraphics) = with(system) {
            sizeRange.forEach { size ->
                paintRange.filter { it != 0 }.forEach {
                    cg.paintString(it, PointForAxes.ZERO.plusAtAndNew(size, it))
                }
            }
        }
    }

    open class InputListenerForCartesianCoordinateSystem(val system: CartesianCoordinateSystem) :
            CoordinateSystem2DInputListener(system) {

        var xAngle = 0.0
        var yAngle = 0.0

        var look: Pair<Number, Number>
            get() = xAngle to yAngle
            set(value) {
                xAngle = value.first.toDouble()
                yAngle = value.second.toDouble()
            }

        override fun mouseDragged(e: MouseEvent) {
            if (system.paintAsBall && firstEvent.button == MouseEvent.BUTTON2) {
                xAngle -= (e.x - lastEvent.x) / 10
                yAngle += (e.y - lastEvent.y) / 10

                system.axes.forEach { (it as BallAxis).resetAngleAndLength(xAngle, yAngle) }
            }

            super.mouseDragged(e)
        }
    }
}