package rainbow.coordinates

import rainbow.component.CoordinateTransformComponent
import rainbow.component.InputListenComponent
import rainbow.point.CoordinatePoint
import rainbow.point.PointDouble
import rainbow.point.PointForAxes
import rainbow.utils.CoordinateGraphics
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

    val size: Int
        get() = axes.size

    init {
        when (size) {
            2 -> addAxes(0, 90)
            3 -> addAxes(225, 0, 90)
        }

    }

    fun addAxes(vararg angles: Number) = angles.forEach { axes.add(Axis(toRadians(it.toDouble()))) }

    /**
     * [angle]使用弧度
     */
    data class Axis(var angle: Double = 0.0, var length: Double = 40.0)

    private class CoordinateTransformComponentForCartesianCoordinateSystem(val system: CartesianCoordinateSystem) :
            CoordinateTransformComponent() {
        override fun toScreenPoint(cp: CoordinatePoint): PointDouble = with(system) {
            val form = cp.toPointForAxes()
            var px = 0.0
            var py = 0.0
            for (i in 0..axes.size - 1) {
                val (angle, length) = axes[i]
                px += cos(angle) * length * form.getValue(i)
                py += sin(angle) * length * form.getValue(i)
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

    private class PainterForCartesianCoordinateSystem(val system: CartesianCoordinateSystem) :
            CoordinateSystemPainter(system) {

        var paintRange = 0..30

        override fun paintOrigin(cg: CoordinateGraphics) = cg.paintString("O", PointForAxes.ZERO)


        override fun paintGrid(cg: CoordinateGraphics) = with(system) {
            //维度遍历
            for (index in 0..axes.size - 1) {
                //值遍历
                for (value in paintRange) {
                    val p0 = PointForAxes.ZERO.plusAtAndNew(index, value)
                    val p1 = p0.plusAtAndNew(if (index == axes.size - 1) 0 else index + 1, 1.0)
                    val p2 = p0.plusAtAndNew(if (index == 0) axes.size - 1 else index - 1, 1.0)

                    cg.paintRayLine(p0, p1)
                    cg.paintRayLine(p0, p2)
                }
            }
        }

        override fun paintAxes(cg: CoordinateGraphics) = with(system) {
            for (index in 0..axes.size - 1) {
                cg.paintStraightLine(PointForAxes.ZERO, PointForAxes.ZERO.plusAtAndNew(index, 10))
            }
        }

        override fun paintNumber(cg: CoordinateGraphics) = with(system) {
            for (index in 0..axes.size - 1) {
                paintRange.filter { it != 0 }.forEach {
                    cg.paintString(it, PointForAxes.ZERO.plusAtAndNew(index, it))
                }
            }
        }
    }

    //TODO more functions
    private class InputListenerForCartesianCoordinateSystem(val system: CartesianCoordinateSystem) : InputListenComponent() {
        override fun mouseDragged(e: MouseEvent) {
            super.mouseDragged(e)
        }
    }
}